package com.sample.domain;

import java.util.Date;

public class AccountUser {
    private Integer id;
    private Account account;
    private User user;

    public AccountUser(){//一定要有构造函数
        this.account = new Account();
        this.user = new User();
    }
    //第一层getter和setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //第二层getter和setter
    // Account properties setter and getter
    public Integer getUid() {
        return account.getUid();
    }
    public void setUid(Integer uid) {
        account.setUid(uid);
    }
    public double getMoney() {
        return account.getMoney();
    }
    public void setMoney(Double money) {
        account.setMoney(money);
    }
    // User properties getter and setter
    public String getUsername() {
        return user.getUsername();
    }
    public void setUsername(String username) {
        user.setUsername(username);
    }
    public Date getBirthday() {
        return user.getBirthday();
    }
    public void setBirthday(Date birthday) {
        user.setBirthday(birthday);
    }
    public String getSex() {
        return user.getSex();
    }
    public void setSex(String sex) {
        user.setSex(sex);
    }
    public String getAddress() {
        return user.getAddress();
    }
    public void setAddress(String address) {
        user.setAddress(address);
    }
}
