package com.example.demo.dao.impl;

import com.example.demo.config.Config;
import com.example.demo.dao.EmberDao;
import com.example.demo.model.Ember;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class EmberDaoImpl implements EmberDao {
    // SQL Statements
    private static final String SELECT_ALL = "SELECT * FROM EMBER";
    private static final String INSERT = "INSERT INTO EMBER (id, nev,) VALUES (?,?)";
    private static final String UPDATE = "UPDATE EMBER SET id=?, nev = ? WHERE id=?";
    private static final String DELETE = "DELETE FROM EMBER WHERE id = ?";
    private String connectionURL;


    public EmberDaoImpl() {
        this.connectionURL = Config.getValue("db.url");
    }

    @Override
    public List<Ember> findAll() {
        List<Ember> emberek = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(connectionURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            ) {

            while (rs.next()){
                Ember e = new Ember();
                e.setNev(rs.getString("nev"));
                e.setId(rs.getInt("id"));

                emberek.add(e);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return emberek;
    }

    @Override
    public Ember save(Ember contact) {
            try(Connection c = DriverManager.getConnection(connectionURL);
                PreparedStatement stmt = contact.getId() <= 0 ? c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE)
            ){
                if(contact.getId() > 0){ // UPDATE
                    stmt.setInt(7, contact.getId());
                }

                stmt.setInt(1, contact.getId());
                stmt.setString(2, contact.getNev());
                int affectedRows = stmt.executeUpdate();
                if(affectedRows == 0){
                    return null;
                }

                if(contact.getId() <= 0){ // INSERT
                    ResultSet genKeys = stmt.getGeneratedKeys();
                    if(genKeys.next()){
                        contact.setId(genKeys.getInt(1));
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }

            return contact;
        }

    @Override
    public void delete(Ember contact) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE);
        ) {
            stmt.setInt(1, contact.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
