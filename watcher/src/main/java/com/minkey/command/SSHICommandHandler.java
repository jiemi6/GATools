package com.minkey.command;

import com.minkey.entity.ConnectInfo;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SysException;
import com.minkey.executer.SSHExecuter;

public class SSHICommandHandler implements ICommandHandler {
    protected ICommand command;

    private ConnectInfo ConnectInfo;

    public SSHICommandHandler(ICommand command, ConnectInfo ConnectInfo) {
        this.command = command;
        this.ConnectInfo = ConnectInfo;
    }


    @Override
    public ResultInfo exec() throws SysException {
        SSHExecuter sshExecuter = new SSHExecuter(ConnectInfo);

        ResultInfo resultInfo = sshExecuter.sendCmd(command.commandStr());

        resultInfo.setUsefulData(command.result2JSON(resultInfo));

        return resultInfo;
    }


}
