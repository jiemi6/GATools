package com.minkey.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.minkey.contants.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * JSON结果返回封装。
 * 
 * @author 
 * 
 */
@Slf4j
public class JSONMessage {
    /** 操作成功 */
    private static final int JSON_RESULT_SUCCESS = ErrorCodeEnum.SUCCESS.getCode();

    /** 操作失败 */
    private static final int JSON_RESULT_FAILED = ErrorCodeEnum.UNKNOWN.getCode();

    /** 状态 */
    private int code = JSON_RESULT_FAILED;

    /** 错误信息描述 */
    private String msg;

    /**
     * 返回数据 
     */
    private JSONObject data;
    
    private long time = System.currentTimeMillis();

	public JSONMessage() {
	}

	private JSONMessage(final int code, final String msg) {
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

    public static JSONMessage createFalied(final ErrorCodeEnum msg) {
        final JSONMessage jsonResult = new JSONMessage(msg.getCode(), msg.getMsg());
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

    public void setCode(int code) {
        this.code = code;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @JSONField(serialize=false)
	public boolean isSuccess(){
        return code == JSONMessage.JSON_RESULT_SUCCESS;
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

    public JSONMessage addData(String key, Object data) {
        if(data == null){
            return this;
        }
	    if(this.data == null){
            this.data = new JSONObject();
        }

        this.data.put(key,JSONObject.toJSON(data));
        return this;
    }

	@Override
	public String toString() {
		try {
			return JSONObject.toJSONString(this);
		} catch (Exception e) {
		    log.error("JSONMessage to String exception",e);
			return JSONMessage.createFalied("return jsonObject to String exception").toString();
		}
	}


	public static JSONMessage string2Obj(String jsonStr){
	    if(StringUtils.isEmpty(jsonStr)){
	        return null;
        }

        //属性必须为public或者有set方法为public
        return JSONObject.parseObject(jsonStr,JSONMessage.class);


    }
}
