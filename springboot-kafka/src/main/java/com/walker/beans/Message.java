package com.walker.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 消息实体类
 *
 * @author Walker
 * @date 2019/3/11 下午8:16
 */
public class Message {

    private Long id;

    private String msg;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
