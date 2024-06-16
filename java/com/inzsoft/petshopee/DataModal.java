package com.inzsoft.petshopee;

public class DataModal {
    private String cat;
    private String img;

    public DataModal() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
