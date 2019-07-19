package com.minkey.exception;


import com.minkey.contants.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 异常基础类
 */
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 6001322206484139531L;
	
	/**
	 * 异常代码
	 */
	private int errorCode;

	public BaseException(int code ,String message) {
			super(StringUtils.isEmpty(message)? ErrorCodeEnum.getErrorCodeEnum(code).getMsg():message);
		this.errorCode = code;
	}
	
	public BaseException(String message) {
		super(StringUtils.isEmpty(message)?ErrorCodeEnum.UNKNOWN.getMsg():message);
		this.errorCode = ErrorCodeEnum.UNKNOWN.getCode();
	}

	public BaseException(String message,Throwable cause) {
		super(StringUtils.isEmpty(message)?ErrorCodeEnum.UNKNOWN.getMsg():message,cause);
		this.errorCode = ErrorCodeEnum.UNKNOWN.getCode();
	}

	public BaseException(int errorCode,String message, Throwable cause)  {
		super(StringUtils.isEmpty(message)? ErrorCodeEnum.getErrorCodeEnum(errorCode).getMsg():message,cause);
        this.errorCode = errorCode;
    }

	public BaseException(BaseException e) {
    	 super(e.getMessage(),e);
         this.errorCode = e.getErrorCode();
	}
	
	public BaseException(Throwable cause)  {
		super(cause.getMessage(),cause);
		this.errorCode = ErrorCodeEnum.UNKNOWN.getCode();
    }
	
	public int getErrorCode() {
		return errorCode;
	}


	public String toLogString() {
		return "BaseException{" +
				"errorCode=" + errorCode +
				"} " + super.getMessage();
	}
}
