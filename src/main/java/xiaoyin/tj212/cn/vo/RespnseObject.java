package xiaoyin.tj212.cn.vo;

/**
 * 用以封装所有的返回对象
 */
public class RespnseObject {
    private boolean success;
    private String message;
    private Object body;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public RespnseObject(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RespnseObject(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
