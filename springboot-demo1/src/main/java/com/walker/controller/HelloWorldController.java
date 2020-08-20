package com.walker.controller;

import com.walker.exception.NullOrEmptyException;
import com.walker.exception.ResultEmptyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello world controller
 *
 * @author Walker
 * @date 2019/2/11 下午1:37
 */
@RestController
@RequestMapping("demo")
public class HelloWorldController {

    @RequestMapping("hello")
    public String index() {
        return "hello world";
    }

    /**
     * 测试异常处理
     *
     * @return
     */
    @RequestMapping("test")
    public Object testHandlerException() {
        throw new RuntimeException("test");
    }

    /**
     * 测试null empty
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("nullOrEmpty")
    public Object testNullOrEmptyException(@RequestParam("param") String param) throws Exception {
        if (StringUtils.isEmpty(param)) {
            throw new NullOrEmptyException("test null or empty");
        }
        if(param.equals("2333")){
            throw new ResultEmptyException("result null or empty");
        }
       return "request is ok";
    }
}
