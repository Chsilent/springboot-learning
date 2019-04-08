package com.walker.common.utils;

public class ResultInfo {
    private Integer status;
    private String msg;
    private Object data;
    private Integer totalpage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public ResultInfo() {
        status = ReturnCodeUtil.ReturnCode.REQUEST_SUCCESS.getStatus();
        msg = ReturnCodeUtil.ReturnCode.REQUEST_SUCCESS.getMsg();
    }

    @Override
    public String toString() {
        return "ResultInfo [status=" + status + ", msg=" + msg + ", data="
                + data + ", totalpage=" + totalpage + "]";
    }
}
