package com.minkey.command;

import com.alibaba.fastjson.JSONObject;
import com.minkey.commands.ICommand;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SysException;
import com.minkey.executer.LocalExecuter;

public class LocalICommandHandler implements ICommandHandler {
    protected ICommand command;

    public LocalICommandHandler(ICommand commandStr) {
        this.command = commandStr;
    }


    @Override
    public ResultInfo exec() throws SysException {
        ResultInfo resultInfo = LocalExecuter.exec(command.commandStr());

        resultInfo.setUsefulData(command.result2JSON(resultInfo));

        return resultInfo;
    }


}
