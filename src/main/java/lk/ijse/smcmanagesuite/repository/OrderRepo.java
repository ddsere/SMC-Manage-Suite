package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Employee;
import lk.ijse.smcmanagesuite.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    public static String currentId() throws SQLException {
        String sql = "SELECT Order_Id FROM Orders ORDER BY Order_Id desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, order.getOrderId());
        pstm.setDate(2, order.getDate());
        pstm.setDouble(3, order.getAmount());
        pstm.setString(4, order.getCusPhone());
        pstm.setString(5, order.getCusName());

        System.out.println(pstm);

        return pstm.executeUpdate() > 0;
    }

    public static List<Order> getAll() throws SQLException {
        String sql = "SELECT * FROM Orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            Date date = resultSet.getDate(2);
            double amout = resultSet.getDouble(3);
            String phone = resultSet.getString(4);
            String name = resultSet.getString(5);

            Order order = new Order(id, date, amout, phone, name);
            orderList.add(order);
        }
        return orderList;
    }
}
