package com.walker.conf;

/**
 * @author Walker
 * @date 2019/7/25 5:11 PM
 */
public enum RabbitMqEnum {

    /**
     * test
     */
    TEST("walker_queue", "walker_exchange", "walker_route_key");

    private String queueName;

    private String exchangeName;

    private String routeKey;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    RabbitMqEnum(String queueName, String exchangeName, String routeKey) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routeKey = routeKey;
    }
}
