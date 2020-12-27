package com.walker.springbootdubboconsumer.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.config.NacosConfigService;
import com.alibaba.nacos.client.naming.NacosNamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Walker
 * @date 2020/12/24 10:15 上午
 */
@Slf4j
@Service
public class NacosDiscoveryService {

    @NacosInjected
    private NacosNamingService nacosNamingService;

    @NacosInjected
    private NacosConfigService nacosConfigService;

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     *
     */
    public void test() throws NacosException {
        List<ServiceInstance> instances = discoveryClient.getInstances("springboot-dubbo-provider");
        // List<Instance> allInstances = nacosNamingService.getAllInstances("springboot-dubbo-provider");
        log.info("server instances num:{}", String.valueOf(instances.size()));
    }


}
