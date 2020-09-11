package com.yq.canal.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 自定义响应数据结构
 * 这个类是提供给门户，ios，安卓，微信商城用的
 * 门户接受此类数据后需要使用本类的方法转换成对应的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */
public class JsonResult {

    public static final Integer ERROR_500 = 500;
    public static final Integer SUCCESS_200 = 200;

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    private Integer count;


    public static JsonResult build(Integer code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult ok(Object data, Integer count) {

        return new JsonResult(data, count);
    }

    public static JsonResult ok(Object data) {

        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorException(String msg) {
        return new JsonResult(555, msg, null);
    }

    public JsonResult() {

    }

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;

    }

    public JsonResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;

    }

    public JsonResult(Object data, Integer count) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
        this.setCount(count);
    }

//    public Boolean isOK() {
//        return this.code == 200;
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}