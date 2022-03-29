package com.example.demo.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Ember {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty nev = new SimpleStringProperty(this, "nev");


    public Ember() {
    }

    public Ember(IntegerProperty id, StringProperty nev) {
        this.id = id;
        this.nev = nev;
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

    public String getNev() {
        return nev.get();
    }

    public StringProperty nevProperty() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev.set(nev);
    }
}
