package com.walker.graceful.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义实现tomcat connector监听器
 *
 * @author Walker
 * @date 2020/8/16 7:56 下午
 */
@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;
    /**
     * 优雅关闭的缓冲期 单位:s
     */
    private static final long GRACEFUL_PERIOD = 30;

    /**
     * 自定义connector
     *
     * @param connector
     */
    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    /**
     * 监听spring应用事件，这里监控上下文关闭事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(GRACEFUL_PERIOD, TimeUnit.SECONDS)) {
                    log.warn("Tomcat thread pool can not shut down gracefully within "
                            + GRACEFUL_PERIOD + " seconds. Application with forceful shutdown");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
