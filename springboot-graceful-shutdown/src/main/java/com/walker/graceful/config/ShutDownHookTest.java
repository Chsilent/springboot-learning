package com.walker.graceful.config;

/**
 * @author Walker
 * @date 2020/8/17 1:41 下午
 */
public class ShutDownHookTest {

    /**
     * addShutdownHook  JAVA提供的可以注册JVM关闭的钩子函数
     * 调用场景：
     * 1）程序正常退出
     * 2）使用System.exit()
     * 3）终端使用ctrl+c触发的中断
     * 4）系统关闭
     * 5）oom宕机
     * 6）使用kill pid杀死进程（kill -15或-2,kill -9不会）
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("主线程启动");
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("接收到关闭信号");
            // 给主线程发送中断信号
            mainThread.interrupt();
            try {
                // 等待主线程正常执行完成
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("优雅关闭完成");
        }));

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // 中断响应
            System.out.println("主线程被中断，处理中断逻辑");
        }
        System.out.println("主线程执行完毕");
    }
}
