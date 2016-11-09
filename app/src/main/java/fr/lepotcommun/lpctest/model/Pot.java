package fr.lepotcommun.lpctest.model;


public class Pot {
    private  String name;
    private  String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Pot{'" + name + "'}";
    }

    //TODO



}
