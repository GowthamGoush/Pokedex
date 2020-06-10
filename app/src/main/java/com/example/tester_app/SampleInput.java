package com.example.tester_app;

public class SampleInput {

    private String text1;
    private String pokeUrl;
    private int pokeId;
    private String pokeImg;

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getPokeUrl() {
        return pokeUrl;
    }

    public void setPokeUrl(String pokeUrl) {
        this.pokeUrl = pokeUrl;
    }

    public int getPokeId() {
        return pokeId;
    }

    public void setPokeId(int pokeId) {
        this.pokeId = pokeId;
    }

    public String getPokeImg() {
        return pokeImg;
    }

    public void setPokeImg(String pokeImg) {
        this.pokeImg = pokeImg;
    }

    public SampleInput(String text1, String pokeUrl, int pokeId , String pokeImg ) {
        this.text1 = text1;
        this.pokeUrl = pokeUrl;
        this.pokeId = pokeId;
        this.pokeImg = pokeImg;

    }
}
