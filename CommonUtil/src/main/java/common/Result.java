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

    public void error(){
        this.success = false;
        this.message = "error";
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
