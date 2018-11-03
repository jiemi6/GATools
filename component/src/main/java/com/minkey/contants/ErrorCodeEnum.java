package com.minkey.contants;

/**
 * 服务器内部错误码定义
 * @author minkey
 *
 */
public enum ErrorCodeEnum {
	/** 成功 */
	SUCCESS(0,"成功"),

	No_license(-100,"无授权证书"),
	No_Login(-200,"无登陆用户"),
	No_Auth(-300,"无权限"),

	/** 未定义错误 */
	UNKNOWN(-1,"未定义错误");
	
	private int code;
	private String msg;

	ErrorCodeEnum(int code ,String msg) {
		this.msg = msg;
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public boolean isSucess(ErrorCodeEnum e){
		return e.getCode() == ErrorCodeEnum.SUCCESS.getCode();
	}
	
	public boolean isSucess(int code){
		return code == ErrorCodeEnum.SUCCESS.getCode();
	}
	
	
	public static ErrorCodeEnum getErrorCodeEnum(int code){
		for(ErrorCodeEnum e : ErrorCodeEnum.values()){
			if(e.getCode() == code){
				return e;
			}
		}
		return ErrorCodeEnum.UNKNOWN;
	}
	
}
