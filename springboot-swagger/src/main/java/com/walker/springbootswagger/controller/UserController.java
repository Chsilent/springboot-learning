package com.walker.springbootswagger.controller;

import com.walker.springbootswagger.bean.User;
import com.walker.springbootswagger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2021/1/8 2:30 下午
 */
@Slf4j
@RestController
@RequestMapping(value = "swagger/api")
@Api(tags = "user crud test")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation("test get user")
    @PostMapping(value = "getUser")
    public Object getUser(User user) {
        return new User().setName(user.getName())
                .setPageNum(user.getPassword());
    }

    @ApiOperation("test find by id")
    @GetMapping(value = "findById")
    public Object findById(Integer id) {
        User user = new User().setName("walker")
                .setPageNum("123456")
                .setId(id);
        return user;
    }
}
