package com.walker.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Walker
 * @date 2019/10/10 8:14 下午
 */
@Service
public class TaskExecutorService {

    @Async
    public void task(String str) {
        System.out.println(Thread.currentThread().getName() + "---" + "start the task.");
        try {
            System.out.println("str-------" + str);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public String testResponse() {
        try {
            Thread.sleep(2000);
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
