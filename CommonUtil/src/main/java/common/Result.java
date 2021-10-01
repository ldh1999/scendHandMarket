package common;
public class Result<T> {
    private boolean success = true;
    private T result;
    private String message;

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
}
