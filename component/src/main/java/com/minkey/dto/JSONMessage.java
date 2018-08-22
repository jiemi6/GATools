package com.minkey.dto;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON结果返回封装。
 * 
 * @author 
 * 
 */
public class JSONMessage {
    static Logger logger = LoggerFactory.getLogger(JSONMessage.class);
    /** 操作成功 */
    private static final int JSON_RESULT_SUCCESS = ErrorCodeEnum.SUCCESS.getCode();

    /** 操作失败 */
    private static final int JSON_RESULT_FAILED = ErrorCodeEnum.UNKNOWN.getCode();

    /** 状态 */
    private int code = JSONMessage.JSON_RESULT_SUCCESS;

    /** 错误信息描述 */
    private String msg;

    /**
     * 返回数据 
     */
    private JSONObject data;
    
    private long time = System.currentTimeMillis();
    
	protected JSONMessage() {
		super();
	}

	private JSONMessage(final int code, final String msg) {
    	super();
    	this.code = code;
    	this.msg = msg;
    }
   
    /**
     * 创建成功的JsonResult对象。
     * 
     * @return
     */
    public static JSONMessage createSuccess() {
        final JSONMessage jsonResult = new JSONMessage(JSONMessage.JSON_RESULT_SUCCESS, null);
        return jsonResult;
    }

    /**
     * 创建成功的JsonResult对象。
     * 
     * @return
     */
    public static JSONMessage createSuccess(String msg) {
        final JSONMessage jsonResult = new JSONMessage(JSONMessage.JSON_RESULT_SUCCESS, msg);
        return jsonResult;
    }
    
    /**
     * 创建失败的JsonResult对象。
     * 
     * @return
     */
    public static JSONMessage createFalied() {
    	final JSONMessage jsonResult = new JSONMessage(JSONMessage.JSON_RESULT_FAILED, "System sneak off.");
    	return jsonResult;
    }

    /**
     * 创建失败的JsonResult对象。
     * 
     * @return
     */
    public static JSONMessage createFalied(final String msg) {
        final JSONMessage jsonResult = new JSONMessage(JSONMessage.JSON_RESULT_FAILED, msg);
        return jsonResult;
    }
    
    /**
     * 自定义失败code
     * @param code  请从标准ErrorCodeEnum中获取
     * @param msg
     * @return
     */
    public static JSONMessage createFalied(final int code, final String msg){
        final JSONMessage jsonResult = new JSONMessage(code, msg);
        return jsonResult;
    }
    
    public int getCode() {
        return code;
    }

    public void setData(JSONObject data) {
		this.data = data;
	}

	public JSONObject getData() {
        return data;
    }

    public long getTime() {
		return time;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 增加数据,放置jsonObject 并转换成string赋值
	 * @param data
	 * @return
	 */
	public JSONMessage addData(JSONObject data) {
		if(data != null){
			this.data = data;
		}
		return this;
	}

    public JSONMessage addData(Object data) {
        if(data != null){
            this.data = (JSONObject) JSONObject.toJSON(data);
        }
        return this;
    }

	@Override
	public String toString() {
		try {
			return JSONObject.toJSONString(this);
		} catch (Exception e) {
		    logger.error("JSONMessage to String exception",e);
			return JSONMessage.createFalied("return jsonObject to String exception").toString();
		}
	}


	public static JSONMessage string2Obj(String jsonStr){
	    if(StringUtils.isEmpty(jsonStr)){
	        return null;
        }
        try{
            return JSONObject.parseObject(jsonStr,JSONMessage.class);
        }catch (Exception e){
            logger.error("String to JSONMessage exception",e);
            return null;
        }

    }
}
