package com.laundry.repo;

import com.laundry.model.PriceChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceChartRepo {

    private static final Connection connection = ConnectionProvider.getConnection();
    private static final String CREATE = "insert into priceChart(typeOfCloth, price) values (?,?)";
    private static final String GET_ALL = "select * from priceChart";
    private static final String DELETE = "delete from priceChart where id = ?";
    private static final String GET_BY_ID = "select * from priceChart where id = ?";
    private static final String UPDATE = "update priceChart set typeOfCloth = ?, price = ? WHERE id = ?";
    private static final String GET_PRICE_BY_TYPE = "select price from priceChart where typeOfCloth = ?";

    public PriceChart create(PriceChart priceChart) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, priceChart.getTypeOfCloth());
            preparedStatement.setInt(2, priceChart.getPrice());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return priceChart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PriceChart> getAll() {
        assert connection != null;
        PreparedStatement preparedStatement;
        List<PriceChart> priceCharts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PriceChart priceChart = new PriceChart();
                priceChart.setId(resultSet.getInt("id"));
                priceChart.setTypeOfCloth(resultSet.getString("typeOfCloth"));
                priceChart.setPrice(resultSet.getInt("price"));
                priceCharts.add(priceChart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceCharts;
    }


    public Boolean delete(Integer id) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public PriceChart getById(Integer id) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PriceChart priceChart = new PriceChart();
                priceChart.setId(resultSet.getInt("id"));
                priceChart.setTypeOfCloth(resultSet.getString("typeOfCloth"));
                priceChart.setPrice(resultSet.getInt("price"));

                return priceChart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PriceChart update(PriceChart priceChart) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, priceChart.getTypeOfCloth());
            preparedStatement.setInt(2, priceChart.getPrice());
            preparedStatement.setInt(3, priceChart.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return priceChart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getPriceByTypeOfCloth(String typeOfCloth) {
        assert connection != null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_PRICE_BY_TYPE);
            preparedStatement.setString(1, typeOfCloth);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Integer.parseInt(resultSet.getString("price"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
