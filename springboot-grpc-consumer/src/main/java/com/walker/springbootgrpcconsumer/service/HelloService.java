package com.walker.springbootgrpcconsumer.service;

import com.walker.springbootgrpcinterface.HelloRequest;
import com.walker.springbootgrpcinterface.HelloServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author Walker
 * @date 2021/7/24 15:36
 */
@Slf4j
@Service
public class HelloService {

    @GrpcClient("helloService")
    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    /**
     * @param name
     * @param sex
     * @return
     */
    public String sayHello(String name, String sex) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).setSex(sex).build();
        return helloServiceBlockingStub.sayHello(request).getMessage();
    }
}
