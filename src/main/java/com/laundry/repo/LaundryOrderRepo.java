package com.laundry.repo;

import com.laundry.enums.LaundryOrderStatus;
import com.laundry.model.LaundryOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LaundryOrderRepo {
    private static final Connection connection = ConnectionProvider.getConnection();

    private static final String COUNT_BY_STATUS = "select count(*) from laundryOrder where status = ?";
    private static final String COUNT_BY_STATUS_USER_ID = "select count(*) from laundryOrder where status = ? and userId = ?";
    private static final String CREATE = "insert into laundryOrder (userName, userId, numberOfCloths, clothItems, status, orderDate, amount) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_BY_STATUS_USER_ID = "select * from laundryOrder where status = ? and userId = ?";
    private static final String GET_BY_STATUS = "select * from laundryOrder where status = ?";
    private static final String UPDATE_STATUS = "update laundryOrder set status = ? where id = ?";
    private static final String GET_BY_ID = "select * from laundryOrder where id = ?";
    private static final String UPDATE_FINISH_DATE = "update laundryOrder set finishDate = ? where id = ?";

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

    public List<LaundryOrder> getAllByStatusAndUserId(LaundryOrderStatus laundryOrderStatus, Integer userId) {
        assert connection != null;
        PreparedStatement preparedStatement;
        List<LaundryOrder> laundryOrders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_BY_STATUS_USER_ID);
            preparedStatement.setString(1, laundryOrderStatus.toString());
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LaundryOrder laundryOrder = new LaundryOrder();
                laundryOrder.setId(resultSet.getInt("id"));
                laundryOrder.setUserName(resultSet.getString("userName"));
                laundryOrder.setUserId(resultSet.getInt("userId"));
                laundryOrder.setNumberOfCloths(resultSet.getInt("numberOfCloths"));
                laundryOrder.setStatus(Enum.valueOf(LaundryOrderStatus.class, resultSet.getString("status")));
                laundryOrder.setOrderDate(LocalDate.parse(resultSet.getString("orderDate")));
                if (resultSet.getString("finishDate") != null)
                    laundryOrder.setFinishDate(LocalDate.parse(resultSet.getString("finishDate")));
                laundryOrder.setClothItems(resultSet.getString("clothItems"));
                laundryOrder.setAmount(resultSet.getInt("amount"));
                laundryOrders.add(laundryOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return laundryOrders;
    }

    public List<LaundryOrder> getAllByStatus(LaundryOrderStatus laundryOrderStatus) {
        assert connection != null;
        PreparedStatement preparedStatement;
        List<LaundryOrder> laundryOrders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_BY_STATUS);
            preparedStatement.setString(1, laundryOrderStatus.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LaundryOrder laundryOrder = new LaundryOrder();
                laundryOrder.setId(resultSet.getInt("id"));
                laundryOrder.setUserName(resultSet.getString("userName"));
                laundryOrder.setUserId(resultSet.getInt("userId"));
                laundryOrder.setNumberOfCloths(resultSet.getInt("numberOfCloths"));
                laundryOrder.setStatus(Enum.valueOf(LaundryOrderStatus.class, resultSet.getString("status")));
                laundryOrder.setOrderDate(LocalDate.parse(resultSet.getString("orderDate")));
                if (resultSet.getString("finishDate") != null)
                    laundryOrder.setFinishDate(LocalDate.parse(resultSet.getString("finishDate")));
                laundryOrder.setClothItems(resultSet.getString("clothItems"));
                laundryOrder.setAmount(resultSet.getInt("amount"));
                laundryOrders.add(laundryOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return laundryOrders;
    }

    public LaundryOrder getById(Integer id) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                LaundryOrder laundryOrder = new LaundryOrder();
                laundryOrder.setId(resultSet.getInt("id"));
                laundryOrder.setUserName(resultSet.getString("userName"));
                laundryOrder.setUserId(resultSet.getInt("userId"));
                laundryOrder.setNumberOfCloths(resultSet.getInt("numberOfCloths"));
                laundryOrder.setStatus(Enum.valueOf(LaundryOrderStatus.class, resultSet.getString("status")));
                laundryOrder.setOrderDate(LocalDate.parse(resultSet.getString("orderDate")));
                if (resultSet.getString("finishDate") != null)
                    laundryOrder.setFinishDate(LocalDate.parse(resultSet.getString("finishDate")));
                laundryOrder.setClothItems(resultSet.getString("clothItems"));
                laundryOrder.setAmount(resultSet.getInt("amount"));
                return laundryOrder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LaundryOrder updateStatus(Integer id, LaundryOrderStatus laundryOrderStatus) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_STATUS);
            preparedStatement.setString(1, laundryOrderStatus.toString());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return getById(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LaundryOrder updateFinishDate(Integer id, LocalDate localDate) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_FINISH_DATE);
            preparedStatement.setString(1, localDate.toString());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return getById(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
