package cn.dcan.constrain;

/**
 * Created by dongc_000 on 2018/4/27.
 */
public class DataResponse<T> extends SimpleResponse {

    private T data;
    public DataResponse(String result, String message) {
        super(result, message);
    }

    public DataResponse(String result, String message,T data) {
        super(result, message);
        this.data=data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
