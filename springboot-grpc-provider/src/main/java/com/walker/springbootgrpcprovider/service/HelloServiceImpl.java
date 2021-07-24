package com.walker.springbootgrpcprovider.service;

import com.walker.springbootgrpcinterface.HelloReply;
import com.walker.springbootgrpcinterface.HelloRequest;
import com.walker.springbootgrpcinterface.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Walker
 * @date 2021/7/24 15:30
 */
@Slf4j
@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        log.info("provider: say hello ...................");
        HelloReply helloReply = HelloReply.newBuilder().setMessage("walker").build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
    }
}
