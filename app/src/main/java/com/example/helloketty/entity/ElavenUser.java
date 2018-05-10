package com.example.helloketty.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class ElavenUser {

    private String name = "";

    private String user_id = "";

    private String gender = "";

    private String phone_number = "";

    private String email = "";

    private String region = "";

    private String address = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {

        JSONObject json = new JSONObject();

        try {

            json.put("name", this.getName());
            json.put("user_id", this.getUser_id());
            json.put("gender", this.getGender());
            json.put("phone_number", this.getPhone_number());
            json.put("email", this.getEmail());
            json.put("region", this.getRegion());
            json.put("address", this.getAddress());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
