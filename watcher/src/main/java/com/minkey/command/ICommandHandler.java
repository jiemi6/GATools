package com.minkey.command;

import com.minkey.entity.ResultInfo;
import com.minkey.exception.SysException;

public interface ICommandHandler {

    ResultInfo exec() throws SysException;


}
