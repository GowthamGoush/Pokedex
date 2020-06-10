package com.example.tester_app;

public class pokeDetail {
    private String name;
    private String height;
    private String weight;
    private String type;
    private String exp;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public pokeDetail(String name, String height, String weight, String type, String exp, String imageUrl) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.exp = exp;
        this.imageUrl = imageUrl;
    }
}
