package com.example.mahas.movein;

public class ListItems {

    private String textHead;
    private  String textD,textD10,textD2,textD3,textD4,textD5,textD6,textD7,textD8,textD9,tname;
    private String URL;
    private String id;
    private boolean liked;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public ListItems(String tname, String textHead, String textD, String textD2, String textD3, String textD4, String textD5, String textD6, String textD7, String URL, String id,boolean liked) {
        this.textHead = textHead;
        this.textD = textD;
        this.tname=tname;
        this.textD2 = textD2;
        this.textD3 = textD3;
        this.textD4 = textD4;
        this.textD5 = textD5;
        this.textD6 = textD6;
        this.textD7 = textD7;
        this.id=id;
        this.URL = URL;
        this.liked=liked;
    }

    public ListItems(String textHead, String textD, String textD2, String textD3, String textD4,String textD10, String textD5, String textD6, String textD7, String textD8,String textD9, String URL,String id,boolean liked) {
        this.textHead = textHead;
        this.textD = textD;
        this.textD2 = textD2;
        this.URL = URL;
        this.textD3=textD3;
        this.textD4=textD4;
        this.textD5=textD5;
        this.textD6=textD6;
        this.textD7=textD7;
        this.textD8=textD8;
        this.textD9=textD9;
        this.textD10=textD10;
        this.id=id;
        this.liked=liked;
    }

    public ListItems(String textHead,String URL) {
        this.textHead = textHead;
        this.URL = URL;
    }

    public String getId() {
        return id;
    }

    public String getTextHead() {
        return textHead;
    }

    public String getTextD() {
        return textD;
    }

    public String getURL() { return URL; }

    public String getTextD2() { return textD2; }

    public String getTextD3(){return textD3;}

    public String getTextD4(){return textD4;}

    public String getTextD5(){return textD5;}


    public String getTextD6(){return textD6;}
    public String getTextD7(){return textD7;}
    public String getTextD8(){return textD8;}
    public String getTextD9(){return textD9;}

    public String getTextD10() {
        return textD10;
    }

    public String getTname() {
        return tname;
    }
}
