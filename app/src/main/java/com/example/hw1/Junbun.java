package com.example.hw1;

import java.io.Serializable;

public class Junbun implements Serializable {
    public String name;
    public String number;
    public String address;

    public Junbun(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
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
    public void setName(String name){
        this.name = name;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public void setAddress(String address){
        this.address = address;
    }

}
