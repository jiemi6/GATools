package com.minkey.exception;


/**
 * 全局系统异常,系统调用使用
 * @author minkey
 *
 */
public class NetException extends BaseException{

	private static final long serialVersionUID = 8870734814939563760L;

	public NetException(BaseException qme) {
		super(qme);
	}

	public NetException(Throwable cause) {
		super(cause);
	}

	public NetException(String msg) {
		super(msg);
	}

	public NetException(int code , String msg) {
		super(code,msg);
	}

	public NetException(int code , String msg, Throwable cause) {
		super(code,msg,cause);
	}

	public NetException(String message, Throwable cause) {
		super(message,cause);
	}

}
