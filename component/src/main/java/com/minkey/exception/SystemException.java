package com.minkey.exception;


import com.minkey.contants.AlarmEnum;

/**
 * 全局系统异常,系统调用使用
 * @author minkey
 *
 */
public class SystemException extends BaseException{

	private static final long serialVersionUID = 8870734814939563760L;

	public SystemException(BaseException qme) {
		super(qme);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String msg) {
		super(msg);
	}
	
	public SystemException(int code ,String msg) {
		super(code,msg);
	}

	public SystemException(AlarmEnum alarmEnum) {
		super(alarmEnum.getAlarmType(),alarmEnum.getDesc());
	}

	public SystemException(AlarmEnum alarmEnum,String msg) {
		super(alarmEnum.getAlarmType(),msg);
	}
	
	public SystemException(int code ,String msg, Throwable cause) {
		super(code,msg,cause);
	}
	
	public SystemException(String message, Throwable cause) {
		super(message,cause);
	}

}
