package com.example.hw1;

import java.util.ArrayList;

public class Cocktail {
    public String strDrink;
    public String strImageSource;
    public String strInstructions;
    public String strDrinkThumb;
    //public String[] strIngredients;
    //public String[] strMeasures;
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;

    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;

    public Cocktail(String strDrink, String strImageSource, String strInstructions, String strDrinkThumb) {
        this.strDrink = strDrink;
        this.strImageSource = strImageSource;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
        //this.strIngredients = strIngredients;
        //this.strMeasures = strMeasures;
    }

    public String getStrDrink(){
        return strDrink;
    }
    public String getStrImageSource(){
        return strImageSource;
    }

    public String getStrDrinkThumb(){
        return strDrinkThumb;
    }

    public String getStrInstructions(){
        return strInstructions;
    }
//    public String[] getStrIngredients(){
//        return strIngredients;
//    }
//    public String[] getStrMeasures(){
//        return strMeasures;
//    }

    public ArrayList<String> getStrIngredients() {
        ArrayList<String> result = new ArrayList<String>();

        if (strIngredient1 == null) {
            return result;
        }
        result.add(strIngredient1);
        if (strIngredient2 == null) {
            return result;
        }
        result.add(strIngredient2);
        if (strIngredient3 == null) {
            return result;
        }
        result.add(strIngredient3);
        if (strIngredient4 == null) {
            return result;
        }
        result.add(strIngredient4);
        if (strIngredient5 == null) {
            return result;
        }
        result.add(strIngredient5);
        if (strIngredient6 == null) {
            return result;
        }
        result.add(strIngredient6);
        if (strIngredient7 == null) {
            return result;
        }
        result.add(strIngredient7);
        if (strIngredient8 == null) {
            return result;
        }
        result.add(strIngredient8);
        if (strIngredient9 == null) {
            return result;
        }
        result.add(strIngredient9);
        if (strIngredient10 == null) {
            return result;
        }
        result.add(strIngredient10);
        if (strIngredient11 == null) {
            return result;
        }
        result.add(strIngredient11);
        if (strIngredient12 == null) {
            return result;
        }
        result.add(strIngredient12);
        if (strIngredient13 == null) {
            return result;
        }
        result.add(strIngredient13);
        if (strIngredient14 == null) {
            return result;
        }
        result.add(strIngredient14);
        if (strIngredient15 == null) {
            return result;
        }
        result.add(strIngredient15);

        return result;
    }

    public ArrayList<String> getStrMeasures() {
        ArrayList<String> result = new ArrayList<String>();

        if (strMeasure1 == null) {
            return result;
        }
        result.add(strMeasure1);
        if (strMeasure2 == null) {
            return result;
        }
        result.add(strMeasure2);
        if (strMeasure3 == null) {
            return result;
        }
        result.add(strMeasure3);
        if (strMeasure4 == null) {
            return result;
        }
        result.add(strMeasure4);
        if (strMeasure5 == null) {
            return result;
        }
        result.add(strMeasure5);
        if (strMeasure6 == null) {
            return result;
        }
        result.add(strMeasure6);
        if (strMeasure7 == null) {
            return result;
        }
        result.add(strMeasure7);
        if (strMeasure8 == null) {
            return result;
        }
        result.add(strMeasure8);
        if (strMeasure9 == null) {
            return result;
        }
        result.add(strMeasure9);
        if (strMeasure10 == null) {
            return result;
        }
        result.add(strMeasure10);
        if (strMeasure11 == null) {
            return result;
        }
        result.add(strMeasure11);
        if (strMeasure12 == null) {
            return result;
        }
        result.add(strMeasure12);
        if (strMeasure13 == null) {
            return result;
        }
        result.add(strMeasure13);
        if (strMeasure14 == null) {
            return result;
        }
        result.add(strMeasure14);
        if (strMeasure15 == null) {
            return result;
        }
        result.add(strMeasure15);

        return result;
    }



    public void setStrDrink(String strDrink){
        this.strDrink = strDrink;
    }
    public void setStrImageSource(String strImageSource){this.strImageSource = strImageSource;}
    public void setStrInstructions(String strInstructions){
        this.strInstructions = strInstructions;
    }
//    public void setStrIngredients(String[] strIngredients){
//        this.strIngredients = strIngredients;
//    }
//    public void setStrMeasures(String[] strMeasures){
//        this.strMeasures = strMeasures;
//    }






}