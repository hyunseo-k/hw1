package com.example.hw1;

import java.io.Serializable;

public class Junbun implements Serializable {
    public String name;
    public String number;
    public String address;
    public int id;

    public Junbun(String name, String number, String address, int id) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.id = id;
    }


    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
    public String getAddress(){
        return address;
    }
    public int getId() { return id; }
    public void setName(String name){
        this.name = name;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setId(int id) { this.id = id; }
}
