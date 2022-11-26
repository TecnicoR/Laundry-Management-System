package com.laundry.repo;

import com.laundry.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepo {

    private static final Connection connection = ConnectionProvider.getConnection();
    private static final String GET_ADMIN_BY_CREDENTIALS = "select * from admin where email = ? and password = ?";

    public Admin getAdmin(String email, String password){
        assert connection != null;
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(GET_ADMIN_BY_CREDENTIALS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Admin admin = new Admin();
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setId(resultSet.getInt("id"));
                return admin;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
