package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Customer;
import lk.ijse.smcmanagesuite.model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String address = resultSet.getString(4);
            String phone = resultSet.getString(5);

            Employee employee = new Employee(id, name, salary, address, phone);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE Emp_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getAddress());
        pstm.setObject(5, employee.getPhone());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET Name = ?, Salary = ?, Address = ?, Phone = ? WHERE Emp_Id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getSalary());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getPhone());
        pstm.setObject(5, employee.getId());

        return pstm.executeUpdate() > 0;
    }

    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE Emp_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Employee employee = null;

        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String address = resultSet.getString(4);
            String phone = resultSet.getString(5);

            employee = new Employee(empId, name, salary, address, phone);
        }
        return employee;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT Emp_Id FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
