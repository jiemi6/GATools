package com.minkey.command;

import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;

public interface ICommandHandler {

    ResultInfo exec() throws SystemException;


}
