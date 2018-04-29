package cn.dcan.constrain;

/**
 * Created by dongc_000 on 2018/4/27.
 */
public class SimpleResponse {

    public static final String OK="ok";
    public static final String ERROR="error";
    public static final String DENIED="denied";

    private String result;
    private String message;

    public SimpleResponse(String result, String message){
        this.result=result;
        this.message=message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
