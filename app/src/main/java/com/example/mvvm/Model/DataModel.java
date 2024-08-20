package com.example.mvvm.Model;

public class DataModel {

    String name;
    String image;
    String sClass;
    String roll;
    String regestarion;
    String faherName;
    String school;
    String gmail;
    String number;

    public DataModel() {
    }

    public DataModel(String name, String image, String sClass, String roll, String regestarion, String faherName, String school, String gmail, String number) {
        this.name = name;
        this.image = image;
        this.sClass = sClass;
        this.roll = roll;
        this.regestarion = regestarion;
        this.faherName = faherName;
        this.school = school;
        this.gmail = gmail;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRegestarion() {
        return regestarion;
    }

    public void setRegestarion(String regestarion) {
        this.regestarion = regestarion;
    }

    public String getFaherName() {
        return faherName;
    }

    public void setFaherName(String faherName) {
        this.faherName = faherName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
