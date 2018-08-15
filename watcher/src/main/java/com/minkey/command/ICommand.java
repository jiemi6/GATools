package com.minkey.command;

import com.alibaba.fastjson.JSONObject;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;

public interface ICommand {

    String commandStr() throws SystemException;

    JSONObject result2JSON(ResultInfo resultInfo) throws SystemException;

}
