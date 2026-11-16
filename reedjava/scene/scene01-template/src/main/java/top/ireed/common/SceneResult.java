package top.ireed.common;

import java.io.Serializable;

/**
 * 场景统一返回结构
 * <p>
 * 所有场景对外接口统一使用本结构返回,便于前端/调用方按固定协议解析。
 *
 * @param <T> 业务数据类型
 */
public class SceneResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务状态码 成功固定为0
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 服务端响应时间戳 毫秒
     */
    private long timestamp;

    public SceneResult() {
        this.timestamp = System.currentTimeMillis();
    }

    public SceneResult(int code, String message, T data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回
     *
     * @param data 业务数据
     * @param <T>  业务数据类型
     * @return 统一返回结构
     */
    public static <T> SceneResult<T> ok(T data) {
        return new SceneResult<>(0, "success", data);
    }

    /**
     * 失败返回
     *
     * @param code    业务状态码
     * @param message 失败原因
     * @param <T>     业务数据类型
     * @return 统一返回结构
     */
    public static <T> SceneResult<T> fail(int code, String message) {
        return new SceneResult<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
