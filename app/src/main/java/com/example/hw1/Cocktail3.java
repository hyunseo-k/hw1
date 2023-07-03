package com.example.hw1;

public class Cocktail3 {
    public String Ingred;
    public String Measure;

    public Cocktail3(String Ingred, String Measure) {
        this.Ingred = Ingred;
        this.Measure = Measure;
    }

    public String getIngred(){
        return Ingred;
    }
    public String getMeasure(){
        return Measure;
    }

    public void setIngred(String Ingred){
        this.Ingred = Ingred;
    }
    public void setMeasure(String Measure){
        this.Measure = Measure;
    }


}
