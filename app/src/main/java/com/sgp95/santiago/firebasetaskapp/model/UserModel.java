package com.sgp95.santiago.firebasetaskapp.model;

import com.sgp95.santiago.firebasetaskapp.util.StringUtils;

public class UserModel extends BaseModel {

    private String userName;
    private boolean isOnline;

    public UserModel() {}

    public UserModel(String userName, boolean isOnline) {
        this.userName = userName;
        this.isOnline = isOnline;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    protected boolean validate() {
        return !StringUtils.stringsAreNullOrEmpty(userName);
    }
}
