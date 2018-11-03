package com.minkey.dto;

public class FTPConfigData extends BaseConfigData{

    private String rootPath;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "FTPConfigData{" +
                "rootPath='" + rootPath + '\'' +
                "} " + super.toString();
    }
}
