package lk.ijse.smcmanagesuite.repository;

import com.sun.javafx.css.CssUtil;
import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Appointment;
import lk.ijse.smcmanagesuite.model.AppointmentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRepo {

    public static boolean save(AppointmentStatus appointmentStatus) throws SQLException {
        String sql = "UPDATE Appointment SET Status = ? WHERE Appointment_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, appointmentStatus.getStatus());
        pstm.setString(2, appointmentStatus.getId());

        System.out.println(pstm);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointment VALUES(?, ?, ?, ?, ?, ?, ?,'Pending')";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        System.out.println(pstm);

        pstm.setObject(1, appointment.getAppId());
        pstm.setObject(2, appointment.getDate());
        pstm.setObject(3, appointment.getCusPhone());
        pstm.setObject(4, appointment.getServId());
        pstm.setObject(5, appointment.getEmpId());
        pstm.setObject(6, appointment.getTimeSlot());
        pstm.setObject(7, appointment.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Appointment WHERE Appointment_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Appointment appointment) throws SQLException {
        String sql = "UPDATE Appointment\n" +
                "SET Date = ?,\n" +
                "    Service_Id = ?,\n" +
                "    Employee_Id = ?,\n" +
                "    Time_Slot = ?\n" +
                "    Price = ?\n" +
                "WHERE Appointment_Id = ?;\n";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, appointment.getDate());
        pstm.setObject(2, appointment.getServId());
        pstm.setObject(3, appointment.getEmpId());
        pstm.setObject(4, appointment.getTimeSlot());
        pstm.setObject(5, appointment.getAppId());
        pstm.setObject(6, appointment.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static int getAppCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS appCount FROM Appointment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int appCount = 0;
        if(resultSet.next()) {
            appCount = resultSet.getInt("appCount");
        }
        return appCount;
    }
}
