package com.laundry.repo;

import com.laundry.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserRepo {
    private static final Connection connection = ConnectionProvider.getConnection();
    private static final String CREATE = "INSERT INTO user (name, email, phoneNumber, password) VALUES (?,?,?,?)";
    private static final String GET_BY_EMAIL = "select * from user where email = ?";
    private static final String UPDATE_BY_ID = "update user set name = ?, email = ?, phoneNumber = ?, password = ? where id = ?";

    private static final String GET_BY_ID = "select * from user where id = ?";

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

    public User getByEmail(String email) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getById(Integer id) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User updateById(Integer id, User user) {
        User byId = getById(id);
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhoneNumber());
            if (user.getPassword() != null) {
                preparedStatement.setString(4, user.getPassword());
                System.out.println("in not null " + user.getPassword());
            } else {
                preparedStatement.setString(4, byId.getPassword());
            }
            preparedStatement.setInt(5, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

