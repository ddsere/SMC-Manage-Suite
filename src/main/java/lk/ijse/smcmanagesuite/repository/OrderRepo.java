package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Order;

import java.sql.*;

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
        pstm.setDouble(3, Double.parseDouble(order.getPrice()));
        pstm.setString(4, order.getCusPhone());
        pstm.setString(5, order.getCusName());

        System.out.println(pstm);

        return pstm.executeUpdate() > 0;
    }
}
