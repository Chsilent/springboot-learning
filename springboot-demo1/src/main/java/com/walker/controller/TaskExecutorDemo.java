package com.walker.controller;

import com.walker.service.TaskExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Walker
 * @date 2019/10/10 5:28 下午
 */
@RestController
@RequestMapping("test")
public class TaskExecutorDemo {

    @Autowired
    private TaskExecutorService taskExecutorService;

    @RequestMapping(value = "testTask", method = RequestMethod.GET)
    public Object testTask() {
        long start = System.currentTimeMillis();
        List<String> list = Arrays.asList("F", "T", "S", "Z", "J", "C");
        list.stream().map(str -> {
            taskExecutorService.task(str);
            return str;
        }).collect(Collectors.toList());
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return list;
    }

    @RequestMapping(value = "testResponse", method = RequestMethod.GET)
    public Object testThreadResponse() {
        Map<String, Object> map = new HashMap<>();
        String result = taskExecutorService.testResponse();
        map.put("data", result);
        return map;
    }
}
