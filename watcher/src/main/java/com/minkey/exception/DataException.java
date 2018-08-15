package com.minkey.exception;


/**
 * 数据处理异常,通常用作数据交互层
 * @author minkey
 *
 */
public class DataException extends BaseException{

	private static final long serialVersionUID = -5909369072293675050L;

	public DataException(BaseException qme) {
		super(qme);
	}
	
	public DataException(Throwable cause) {
		super(cause);
	}
	
	public DataException(String msg) {
		super(msg);
	}
	
	public DataException(int code ,String msg) {
		super(code,msg);
	}
	
	public DataException(int code ,String msg, Throwable cause) {
		super(code,msg,cause);
	}
	
	public DataException(String message, Throwable cause) {
		super(message,cause);
	}

}
