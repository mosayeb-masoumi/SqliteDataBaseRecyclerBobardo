package com.example.tornado.databaserecycler_bobardo.model;

public class UserModel {

    int id;
    String name;
    String family;
//    String color;
    int living_status;


    public UserModel() {
    }

    public UserModel(String name, String family,int living_status) {
//    public UserModel(String name, String family, String color ,  int living_status) {
        this.name = name;
        this.family = family;
//        this.color = color;
        this.living_status = living_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    public int getLiving_status() {
        return living_status;
    }

    public void setLiving_status(int living_status) {
        this.living_status = living_status;
    }
}
