package com.walker.graceful.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Walker
 * @date 2020/8/16 7:56 下午
 */
@Configuration
public class GracefulShutdownBeanFactory {

    /**
     * gracefulShutdown bean
     *
     * @return
     */
    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    /**
     * spring webServerFactory，增加自定义的监听器
     *
     * @param gracefulShutdown
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final GracefulShutdown gracefulShutdown) {
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        webServerFactory.addConnectorCustomizers(gracefulShutdown);
        return webServerFactory;
    }
}
