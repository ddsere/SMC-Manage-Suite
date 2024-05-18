package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.ItemQty;
import lk.ijse.smcmanagesuite.model.Order;
import lk.ijse.smcmanagesuite.model.ServiceIds;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDetailRepo {

    public static boolean save(Order order, List<ServiceIds> serviceIds) throws SQLException {
            for (ServiceIds od : serviceIds) {
                if(!save(od,order)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean save( ServiceIds od, Order order) throws SQLException {
            String sql = "INSERT INTO service_orders VALUES(?, ?)";
            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setString(1, od.getId());
            pstm.setString(2, order.getOrderId());

            return pstm.executeUpdate() > 0;
        }
    }
