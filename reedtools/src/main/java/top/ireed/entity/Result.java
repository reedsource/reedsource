/*
 * FileName: Result
 * Author:   reedsource
 */
package top.ireed.entity;

import java.util.List;

import static top.ireed.general.TopConstant.*;

/**
 * 功能简述:
 * 〈数据返回工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020-10-16 09:18:12
 * reedsource@189.cn
 */
public class Result<T> {
	/**
	 * 调用是否成功标识，0：成功，-1:系统繁忙
	 */
	private int code;
	/**
	 * 调用结果
	 */
	private T data;
	/**
	 * 参数或错误的集合
	 */
	private List<String> solutions;
	/**
	 * 结果消息，如果调用成功，消息通常为空
	 */
	private String msg;

	public Result() {
		super();
	}

	/**
	 * 标准返回
	 *
	 * @param errCode 返回码
	 * @param data    数据
	 * @param msg  返回信息
	 */
	public Result(int errCode, T data, String msg) {
		this.code = errCode;
		this.data = data;
		this.msg = msg;
	}


	/**
	 * 信息返回
	 *
	 * @param errcode 返回码
	 * @param errmsg  返回信息
	 */
	public Result(int errcode, String errmsg) {
		this.code = errcode;
		this.msg = errmsg;
	}

	/**
	 * 数据返回
	 *
	 * @param errcode   返回码
	 * @param solutions 返回信息List集合
	 */
	public Result(int errcode, List<String> solutions) {
		this.code = errcode;
		this.solutions = solutions;
	}

	/**
	 * 请求成功消息
	 *
	 * @param msg 结果
	 * @return RPC调用结果
	 */
	public static <E> Result<E> successMsg(String msg) {
		return new Result<>(SUCCESS_CODE, null, msg);
	}

	/**
	 * 请求成功消息
	 *
	 * @param data 结果
	 * @return RPC调用结果
	 */
	public static <E> Result<E> success(E data) {
		return new Result<>(SUCCESS_CODE, data, SUCCESS_MSG);
	}


	/**
	 * 请求成功消息
	 *
	 * @return RPC调用结果
	 */
	public static <E> Result<E> success() {
		return new Result<>(SUCCESS_CODE, SUCCESS_MSG);
	}

	/**
	 * 请求成功消息  自定义返回码
	 *
	 * @param code 自定义返回码
	 * @param data 返回数据
	 * @param <E>  数据
	 * @return 信息
	 */
	public static <E> Result<E> success(int code, E data) {
		return new Result<>(code, data, SUCCESS_MSG);
	}

	/**
	 * 请求成功方法
	 *
	 * @param data 结果
	 * @param msg  消息
	 * @return RPC调用结果
	 */
	public static <E> Result<E> success(E data, String msg) {
		return new Result<>(SUCCESS_CODE, data, msg);
	}

	/**
	 * 标准返回
	 *
	 * @param code 错误码
	 * @param msg  错误信息
	 * @param <E>  类
	 * @return 返回实体
	 */
	public static <E> Result<E> fail(int code, String msg) {
		return new Result<>(code, null, (msg == null || msg.isEmpty()) ? ERROR_MSG : msg);
	}

	/**
	 * 返回错误信息
	 *
	 * @param msg 返回信息
	 * @param <E> 类
	 * @return 返回实体
	 */
	public static <E> Result<E> fail(String msg) {
		return fail(FAIL_CODE, msg);
	}

	/**
	 * 返回错误异常信息
	 *
	 * @param throwable 异常信息
	 * @return RPC调用结果
	 */
	public static <E> Result<E> fail(Throwable throwable) {
		return fail(throwable != null ? throwable.getMessage() : ERROR_MSG);
	}

	/**
	 *  超时信息
	 * @param <E> 类
	 * @return 返回实体
	 */
	public static <E> Result<E> timeout() {
		return fail(TIMEOUT_MSG);
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<String> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<String> solutions) {
		this.solutions = solutions;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", data=" + data +
				", solutions=" + solutions +
				", msg='" + msg + '\'' +
				'}';
	}
}
