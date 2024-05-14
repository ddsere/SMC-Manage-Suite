package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentRepo {
    public static boolean save(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointment VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, appointment.getAppId());
        pstm.setObject(2, appointment.getDate());
        pstm.setObject(3, appointment.getCusPhone());
        pstm.setObject(4, appointment.getServId());
        pstm.setObject(5, appointment.getEmpId());
        pstm.setObject(6, appointment.getTimeSlot());

        return pstm.executeUpdate() > 0;
    }
}
