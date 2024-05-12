package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemwithSupplier;
import lk.ijse.smcmanagesuite.model.Service;
import lk.ijse.smcmanagesuite.model.ServicewithEmployee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicewithEmployeeRepo {

    public static List<ServicewithEmployee> getAll() throws SQLException {
        String sql = "SELECT s.*, e.Name FROM Service s INNER JOIN Employee e ON s.Emp_Id = e.Emp_Id;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ServicewithEmployee> servicewithEmployeeList = new ArrayList<>();
        while (resultSet.next()) {
            String serviceId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);
            String empName = resultSet.getString(5);

            ServicewithEmployee servicewithEmployee = new ServicewithEmployee(serviceId,description,price,empId,empName);
            servicewithEmployeeList.add(servicewithEmployee);
        }
        return servicewithEmployeeList;
    }

    public static ServicewithEmployee searchById(String id) throws SQLException {
        String sql = "SELECT s.*, e.Name FROM Service s INNER JOIN Employee e ON s.Emp_Id = e.Emp_Id WHERE S_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        ServicewithEmployee servicewithEmployee = null;

        if (resultSet.next()) {
            String sId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);
            String empName = resultSet.getString(5);

            servicewithEmployee = new ServicewithEmployee(sId, description, price, empId,empName);
        }
        return servicewithEmployee;
    }
}
