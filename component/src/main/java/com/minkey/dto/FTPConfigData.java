package com.minkey.dto;

public class FTPConfigData extends BaseConfigData{

    private String rootPath;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        if(!rootPath.endsWith("/")){
            //增加斜杠
            this.rootPath = rootPath+"/";
        }
    }

    @Override
    public String toString() {
        return "FTPConfigData{" +
                "rootPath='" + rootPath + '\'' +
                "} " + super.toString();
    }
}
