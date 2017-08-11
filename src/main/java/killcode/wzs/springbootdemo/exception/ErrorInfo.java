package killcode.wzs.springbootdemo.exception;

import lombok.Builder;

@Builder
public class ErrorInfo implements IError {
    private int code;
    private String msg;
    private String url;

    public String getUrl() {
        return url;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
