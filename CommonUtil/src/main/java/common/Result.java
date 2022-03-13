package common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private boolean success = true;
    private T result;
    private String message;
    /**
     * 401：token失效
     * 402: 权限不足
     * 600: sign认证失败
     */
    private int code;

    public void succcess(T result){
        this.success = true;
        this.message = "success";
        this.result = result;
    }

    public void succcess(String message){
        this.success = true;
        this.message = message;
    }

    public void error(){
        this.success = false;
        this.message = "error";
    }

    public void error(String message){
        this.success = false;
        this.message = message;
    }

    public static<T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }

    public static<T> Result<T> OK(T t) {
        Result<T> r = new Result<T>();
        r.setResult(t);
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }

    public static<T> Result<T> ERROR() {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
