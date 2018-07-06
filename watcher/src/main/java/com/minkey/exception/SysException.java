package com.minkey.exception;


public class SysException extends Exception{
  private Throwable cause=null;
  public SysException () {
    super();
  }

  public SysException (String s) {
    super(s);
  }
  public SysException (String s, Throwable e) {
    super(s);
    this.cause=e;
  }

  @Override
  public Throwable getCause(){
    return this.cause;
  }
}
