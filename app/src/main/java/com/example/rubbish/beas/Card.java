package com.example.rubbish.beas;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = -5376313495678563362L;

    public String title;
    public String info;
    public int imageSrc;
    public int id;

    public Card(){}

    public Card(String title,String info,int imageSrc,int id){
        this.title = title;
        this.info = info;
        this.imageSrc = imageSrc;
        this.id = id;
    }

    public void setTitle(String title,String info){
        this.title = title;
        this.info = info;
    }
}
