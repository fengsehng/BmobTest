package com.example.euler_kalvinhe.bmobtest;

import cn.bmob.v3.BmobObject;

/**
 * Created by Euler-KalvinHe on 2015/8/24.
 */
public class User extends BmobObject{
    private String userName;
    private String phoneNumber;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
