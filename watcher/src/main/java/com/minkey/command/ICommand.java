package com.minkey.command;

import com.alibaba.fastjson.JSONObject;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SysException;

public interface ICommand {

    String commandStr() throws SysException;

    JSONObject result2JSON(ResultInfo resultInfo) throws SysException;

}
