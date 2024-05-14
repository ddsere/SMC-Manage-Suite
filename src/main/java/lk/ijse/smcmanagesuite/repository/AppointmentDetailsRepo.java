package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.AppointmentDetails;
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
        String sql = "SELECT a.Appointment_Id, a.Customer_Phone, s.Name AS ServiceName, a.Date, a.Time_Slot, e.Name AS EmployeeName FROM Appointment a INNER JOIN Service s ON a.Service_Id = s.S_Id INNER JOIN Employee e ON a.Employee_Id = e.Emp_Id;";

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
}
