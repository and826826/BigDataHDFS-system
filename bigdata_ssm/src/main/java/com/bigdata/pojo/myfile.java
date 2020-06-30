package com.bigdata.pojo;

import javax.xml.crypto.Data;

public class myfile {
    String name;
    String permission;
    String LastModified;
    boolean isDirectory;

    public myfile(String name, String permission, String lastModified, boolean isDirectory) {
        this.name = name;
        this.permission = permission;
        LastModified = lastModified;
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getLastModified() {
        return LastModified;
    }

    public void setLastModified(String lastModified) {
        LastModified = lastModified;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    @Override
    public String toString() {
        return "myfile{" +
                "name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                ", LastModified='" + LastModified + '\'' +
                ", isDirectory=" + isDirectory +
                '}';
    }
}
