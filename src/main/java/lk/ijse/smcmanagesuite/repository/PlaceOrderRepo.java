package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Order;
import lk.ijse.smcmanagesuite.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());
            if (isOrderSaved) {
                System.out.println("order saved");
                boolean isUpdateAppStatus = AppointmentRepo.save(po.getAppoStatus());
                if (isUpdateAppStatus) {
                    System.out.println("isupdatstatue " + isUpdateAppStatus);
                        connection.commit();
                        return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
