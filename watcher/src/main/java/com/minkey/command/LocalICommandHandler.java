package com.minkey.command;

import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;
import com.minkey.executer.LocalExecuter;

public class LocalICommandHandler implements ICommandHandler {
    protected ICommand command;

    public LocalICommandHandler(ICommand commandStr) {
        this.command = commandStr;
    }


    @Override
    public ResultInfo exec() throws SystemException {
        ResultInfo resultInfo = LocalExecuter.exec(command.commandStr());

        resultInfo.setUsefulData(command.result2JSON(resultInfo));

        return resultInfo;
    }


}
