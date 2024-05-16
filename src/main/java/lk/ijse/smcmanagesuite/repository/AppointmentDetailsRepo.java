package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.AppointmentDetails;
import lk.ijse.smcmanagesuite.model.AppointmentSearch;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemwithSupplier;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDetailsRepo {
    public static List<AppointmentDetails> getAll() throws SQLException {
        String sql = "SELECT \n" +
                "  a.Appointment_Id,\n" +
                "  c.Name AS CustomerName,\n" +
                "  s.Name AS ServiceName,\n" +
                "  a.Date,\n" +
                "  a.Time_Slot,\n" +
                "  e.Name AS EmployeeName\n" +
                "FROM Appointment a\n" +
                "INNER JOIN Customer c ON a.Customer_Phone = c.Phone\n" +
                "INNER JOIN Service s ON a.Service_Id = s.S_Id\n" +
                "INNER JOIN Employee e ON a.Employee_Id = e.Emp_Id;\n";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            String appId = resultSet.getString(1);
            String cusPhone = resultSet.getString(2);
            String servName = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            String ts = resultSet.getString(5);
            String empName = resultSet.getString(6);

            AppointmentDetails appointmentDetails = new AppointmentDetails(appId, cusPhone, servName, date, ts, empName);
            appointmentDetailsList.add(appointmentDetails);
        }
        return appointmentDetailsList;
    }

    public static AppointmentSearch searchById(String cusPhone) throws SQLException {
        String sql = "SELECT \n" +
                "  a.Appointment_Id AS AppId,\n" +
                "  a.Date,\n" +
                "  a.Service_Id AS SId,\n" +
                "  a.Employee_Id AS EmpId,\n" +
                "  a.Time_Slot,\n" +
                "  c.Name AS CusName,\n" +
                "  e.Name AS EmpName,\n" +
                "  s.Name AS SName\n" +
                "FROM Appointment a\n" +
                "INNER JOIN Customer c ON a.Customer_Phone = c.Phone\n" +
                "INNER JOIN Service s ON a.Service_Id = s.S_Id\n" +
                "INNER JOIN Employee e ON a.Employee_Id = e.Emp_Id\n" +
                "WHERE a.Customer_Phone = ?;\n";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, cusPhone);
        ResultSet resultSet = pstm.executeQuery();

        AppointmentSearch appointmentSearch = null;

        if (resultSet.next()) {
            String appId = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String sId = resultSet.getString(3);
            String empId = resultSet.getString(4);
            String ts = resultSet.getString(5);
            String cusName = resultSet.getString(6);
            String empName = resultSet.getString(7);
            String sName = resultSet.getString(8);

            appointmentSearch = new AppointmentSearch(appId, date.toLocalDate(), sId, empId, ts, cusName, empName, sName);
        }
        return appointmentSearch;
    }
}
