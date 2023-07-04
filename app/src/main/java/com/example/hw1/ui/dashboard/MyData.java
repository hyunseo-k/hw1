package com.example.hw1.ui.dashboard;

public class MyData {
    String img_src;
    String img_text;
    int img_id;

    public MyData(String img_src, String img_text, int img_id) {
        this.img_src = img_src;
        this.img_text = img_text;
        this.img_id = img_id;
    }

    public String getSrc() {
        return this.img_src;
    }

    public String getText() {
        return this.img_text;
    }
    public int getId() {
        return this.img_id;
    }

    public void setSrc(String img_src) {
        this.img_src = img_src;
    }

    public void setText(String img_text) {
        this.img_text = img_text;
    }

    public void setId(int img_id) {
        this.img_id = img_id;
    }

}