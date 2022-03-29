package com.example.demo.dao;

import com.example.demo.model.Phone;

import java.util.List;

public interface PhoneDao {

    List<Phone> findall();
    Phone save(Phone phone);
    void delete(Phone phone);
}
