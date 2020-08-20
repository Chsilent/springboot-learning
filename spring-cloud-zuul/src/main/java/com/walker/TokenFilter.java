package com.walker;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author Walker
 * @date 2019/4/16 4:50 PM
 */
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //请求被路由之前调用
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //filter执行顺序，数字越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    /**
     * 具体的filter执行方法
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
