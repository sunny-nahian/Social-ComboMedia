package com.sunny.bsdproject1;

public class RegisterDataModel {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String conpassword;

    //Defalte Constractor


    public RegisterDataModel(String name, String email, String phone, String password, String conpassword) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.conpassword = conpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }
}