package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.ItemQty;
import lk.ijse.smcmanagesuite.model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDetailRepo {
    public static boolean save(List<ItemQty> itemQties , Order order) throws SQLException {
        for (ItemQty od : itemQties) {
            if(!save(od,order)) {
                return false;
            }
        }
        return true;
    }

    public static boolean save( ItemQty od, Order order) throws SQLException {
        String sql = "INSERT INTO item_orders VALUES(?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOrderId());
        pstm.setString(2, od.getItemCode());

        return pstm.executeUpdate() > 0;
    }
}
