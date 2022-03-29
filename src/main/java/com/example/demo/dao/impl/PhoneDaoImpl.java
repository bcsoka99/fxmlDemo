package com.example.demo.dao.impl;

import com.example.demo.config.Config;
import com.example.demo.dao.PhoneDao;
import com.example.demo.model.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {
    private String connectionString;
    private static final String findall = "SELECT * FROM PHONE";
    private static final String INSERT = "INSERT INTO PHONE (id, number,) VALUES (?,?)";
    private static final String delete = "DELETE FROM PHONE WHERE ID = ?";
    private static final String update = "UPDATE PHONE SET ID=? number=? WHERE ID=?";

    public PhoneDaoImpl(){
        this.connectionString = Config.getValue("db.url");
    }

    @Override
    public List<Phone> findall() {
        List<Phone> returnlist = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(findall);
        ){
            while(rs.next()){
                Phone p = new Phone();
                p.setId(rs.getInt("id"));
                p.setNumber(rs.getString("number"));

                returnlist.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return returnlist;
    }

    @Override
    public Phone save(Phone phone) {
        try(Connection c = DriverManager.getConnection(connectionString);
            PreparedStatement stmt = phone.getId() <= 0 ? c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(update)
        ){
            if(phone.getId() > 0){ // UPDATE
                stmt.setInt(7, phone.getId());
            }

            stmt.setInt(1, phone.getId());
            stmt.setString(2, phone.getNumber());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(phone.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    phone.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return phone;
    }

    @Override
    public void delete(Phone phone) {
        try(Connection c = DriverManager.getConnection(connectionString);
            PreparedStatement stmt = c.prepareStatement(delete);
        ) {
            stmt.setInt(1, phone.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
