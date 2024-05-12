package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.Service;
import lk.ijse.smcmanagesuite.model.ServicewithEmployee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceRepo {

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Service WHERE S_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Service service) throws SQLException {
        String sql = "INSERT INTO Service VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, service.getServiceId());
        pstm.setObject(2, service.getDescription());
        pstm.setObject(3, service.getPrice());
        pstm.setObject(4, service.getEmpId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Service service) throws SQLException {
        String sql = "UPDATE Service SET Emp_Id = ?, Name = ?, Price = ? WHERE S_Id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, service.getEmpId());
        pstm.setObject(2, service.getDescription());
        pstm.setObject(3, service.getPrice());
        pstm.setObject(4, service.getServiceId());

        return pstm.executeUpdate() > 0;
    }

    public static Service searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Service WHERE S_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Service service = null;

        if (resultSet.next()) {
            String sId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);

            service = new Service(sId, description, price, empId);
        }
        return service;
    }
}
