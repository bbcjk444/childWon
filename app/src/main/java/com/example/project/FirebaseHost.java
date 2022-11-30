package com.example.project;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHost {
    public String p_id;
    public String p_name;
    public String p_pw;
    public String p_pwc;
    public String p_add;
    public String p_phone;
    public String p_phone2;
    public String p_image;

    public FirebaseHost(String p_name,String p_id,String p_pw,String p_pwc,String p_add,String p_phone,String p_phone2,String p_image){
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_pw = p_pw;
        this.p_pwc = p_pwc;
        this.p_add = p_add;
        this.p_phone = p_phone;
        this.p_phone2 = p_phone2;
        this.p_image = p_image;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_pw() {
        return p_pw;
    }

    public void setP_pw(String p_pw) {
        this.p_pw = p_pw;
    }

    public String getP_pwc() {
        return p_pwc;
    }

    public void setP_pwc(String p_pwc) {
        this.p_pwc = p_pwc;
    }

    public String getP_add() {
        return p_add;
    }

    public void setP_add(String p_add) {
        this.p_add = p_add;
    }

    public String getP_phone() {
        return p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public String getP_phone2() {
        return p_phone2;
    }

    public void setP_phone2(String p_phone2) {
        this.p_phone2 = p_phone2;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    @Override
    public String toString() {
        return "FirebaseHost{" +
                "p_id='" + p_id + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_pw='" + p_pw + '\'' +
                ", p_pwc='" + p_pwc + '\'' +
                ", p_add='" + p_add + '\'' +
                ", p_phone='" + p_phone + '\'' +
                ", p_phone2='" + p_phone2 + '\'' +
                ", p_image='" + p_image + '\'' +
                '}';
    }
}
