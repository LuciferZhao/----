package com.org.tybar.config;


public class Result {
	private Integer code;
	private String message;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Result success() {
		Result r = new Result();
		r.setCode(ResultCode.SUCCESS.getCode());
		r.setMessage(ResultCode.SUCCESS.getMessage());
		return r;

	}

	public static Result success(String message) {
		Result r = new Result();
		r.setCode(ResultCode.SUCCESS.getCode());
		r.setMessage(message);
		return r;
	}

	public static Result success(String message, Object data) {
		Result r = new Result();
		r.setCode(ResultCode.SUCCESS.getCode());
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	public static Result fail() {
		Result r = new Result();
		r.setCode(ResultCode.FAIL.getCode());
		r.setMessage(ResultCode.FAIL.getMessage());
		return r;
	}

	public static Result fail(String message) {
		Result r = new Result();
		r.setCode(ResultCode.FAIL.getCode());
		r.setMessage(message);
		return r;
	}

	public static Result fail(String message, Object data) {
		Result r = new Result();
		r.setCode(ResultCode.FAIL.getCode());
		r.setMessage(message);
		return r;
	}
}
