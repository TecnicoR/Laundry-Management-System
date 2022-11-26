package com.laundry.repo;

import com.laundry.enums.LaundryOrderStatus;
import com.laundry.model.LaundryOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LaundryOrderRepo {
    private static final Connection connection = ConnectionProvider.getConnection();

    private static final String COUNT_BY_STATUS = "select count(*) from laundryOrder where status = ?";
    private static final String COUNT_BY_STATUS_USER_ID = "select count(*) from laundryOrder where status = ? and userId = ?";
    private static final String CREATE = "insert into laundryOrder (userName, userId, numberOfCloths, clothItems, status, orderDate, amount) VALUES (?,?,?,?,?,?,?)";

    public Integer countByStatus(LaundryOrderStatus laundryOrderStatus) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(COUNT_BY_STATUS);
            preparedStatement.setString(1, laundryOrderStatus.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer countByStatusAndUserId(LaundryOrderStatus laundryOrderStatus, Integer userId) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(COUNT_BY_STATUS_USER_ID);
            preparedStatement.setString(1, laundryOrderStatus.toString());
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LaundryOrder create(LaundryOrder laundryOrder) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, laundryOrder.getUserName());
            preparedStatement.setInt(2, laundryOrder.getUserId());
            preparedStatement.setInt(3, laundryOrder.getNumberOfCloths());
            preparedStatement.setString(4, laundryOrder.getClothItems());
            preparedStatement.setString(5, laundryOrder.getStatus().toString());
            preparedStatement.setString(6, laundryOrder.getOrderDate().toString());
            preparedStatement.setInt(7, laundryOrder.getAmount());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return laundryOrder;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
