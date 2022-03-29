package com.example.demo.model;

import javafx.beans.property.*;

public class Phone {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty number = new SimpleStringProperty(this, "number");

    public Phone() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }
}
