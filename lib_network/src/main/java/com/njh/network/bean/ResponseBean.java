package com.njh.network.bean;


/**
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 *
 * @author niejiahuan
 */
public class ResponseBean<T> {

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int returnCode;

    /**
     * 数据对象[成功返回对象,失败返回错误说明]
     */
    private T data;

    private boolean Secure;

    private String ReturnTime;

    /**
     * 是否成功(这里约定200)
     *
     * @return
     */
    public boolean isSuccess() {
        return returnCode == 200;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "msg='" + msg + '\'' +
                ", returnCode=" + returnCode +
                ", data=" + data +
                ", Secure=" + Secure +
                ", ReturnTime=" + ReturnTime +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSecure() {
        return Secure;
    }

    public void setSecure(boolean secure) {
        Secure = secure;
    }

    public String getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(String returnTime) {
        ReturnTime = returnTime;
    }
}
