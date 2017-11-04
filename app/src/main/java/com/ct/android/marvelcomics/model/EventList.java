package com.ct.android.marvelcomics.model;

import java.util.List;

public class EventList {
    private int available;
    private int returned;
    private String collectionURI;
    private List<Summary> items;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Summary> getItems() {
        return items;
    }

    public void setItems(List<Summary> items) {
        this.items = items;
    }

    public class Summary {
        private String resourceURI;
        private String name;
    }
}
