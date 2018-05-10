package cn.bitzo.bms.entity;

public class MyResponse {
    private int status;
    private boolean isSuccess;
    private String msg;
    private Object data;

    /**
     *
     * @param status
     * @param isSuccess
     * @param msg
     * @param data
     */
    public MyResponse(int status, boolean isSuccess, String msg, Object data) {
        this.status = status;
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public MyResponse() {
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "status=" + status +
                ", isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
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
}
