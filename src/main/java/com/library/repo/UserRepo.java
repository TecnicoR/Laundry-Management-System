package com.library.repo;

import com.library.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserRepo {
    private static final Connection connection = ConnectionProvider.getConnection();
    private static final String CREATE = "INSERT INTO `user` (`name`, `email`, `phoneNumber`, `password`) VALUES (?,?,?,?');";
    private static final String GET_BY_EMAIL = "select * from user where email = ?";


    public User create(User user) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(CREATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmail(String email){
        assert connection != null;
        PreparedStatement preparedStatement;
        try{
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

