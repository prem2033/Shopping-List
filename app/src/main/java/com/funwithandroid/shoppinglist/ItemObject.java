package com.funwithandroid.shoppinglist;

public class ItemObject {
    String item_name;
    String item_quality;
    int marked;

    public ItemObject(String item_name, String item_quality,int marked) {
        this.item_name = item_name;
        this.item_quality = item_quality;
        this.marked=marked;
    }
    //default constructor
    public ItemObject() {
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_quality() {
        return item_quality;
    }

    public void setItem_quality(String item_quality) {
        this.item_quality = item_quality;
    }

    public int getMarked() {
        return marked;
    }

    public void setMarked(int marked) {
        this.marked = marked;
    }
}
