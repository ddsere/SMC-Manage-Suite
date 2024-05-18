package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {

    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());

            if (isOrderSaved) {
                boolean isItemDetailsUpdate = ItemDetailRepo.save(po.getItemQties(),po.getOrder());

                if (isItemDetailsUpdate){
                    boolean isItemQtyUpdate = ItemRepo.updateQty(po.getItemQties());

                    if (isItemQtyUpdate){
                        boolean isServiceDetailsUpdated =ServiceDetailRepo.save(po.getOrder(),po.getServiceIds());

                    }
                    if (isItemQtyUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
