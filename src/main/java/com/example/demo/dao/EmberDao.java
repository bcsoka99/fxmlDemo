package com.example.demo.dao;

import com.example.demo.model.Ember;

import java.util.List;

public interface EmberDao {

    List<Ember> findAll();
    Ember save(Ember contact);
    void delete(Ember contact);

}
