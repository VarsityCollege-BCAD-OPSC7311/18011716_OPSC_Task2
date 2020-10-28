package com.task2.task2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    String firstName;
    String lastName;
    String username;
    String password;
    String height;
    String weight;
    String targetweight;
    ArrayList<Double> dailyweight;
    String system;


    public User() { }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTargetweight() {
        return targetweight;
    }

    public void setTargetweight(String targetweight) {
        this.targetweight = targetweight;
    }

    public List<Double> getDailyweight() {
        return dailyweight;
    }

    public void setDailyweight(ArrayList<Double> dailyweight) {
        this.dailyweight = dailyweight;
    }

    public String getCalorieintake() {
        return calorieintake;
    }

    public void setCalorieintake(String calorieintake) {
        this.calorieintake = calorieintake;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    ArrayList<String> images;

    String calorieintake;

}
