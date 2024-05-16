package lk.ijse.smcmanagesuite.repository;

import com.sun.javafx.css.CssUtil;
import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentRepo {
    public static boolean save(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointment VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        System.out.println(pstm);

        pstm.setObject(1, appointment.getAppId());
        pstm.setObject(2, appointment.getDate());
        pstm.setObject(3, appointment.getCusPhone());
        pstm.setObject(4, appointment.getServId());
        pstm.setObject(5, appointment.getEmpId());
        pstm.setObject(6, appointment.getTimeSlot());

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
                "WHERE Appointment_Id = ?;\n";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, appointment.getDate());
        pstm.setObject(2, appointment.getServId());
        pstm.setObject(3, appointment.getEmpId());
        pstm.setObject(4, appointment.getTimeSlot());
        pstm.setObject(5, appointment.getAppId());

        return pstm.executeUpdate() > 0;
    }
}
