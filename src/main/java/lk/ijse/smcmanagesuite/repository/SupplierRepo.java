package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Customer;
import lk.ijse.smcmanagesuite.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {

    public static boolean save(Supplier supplier) throws SQLException {
//        In here you can now save your supplier
        String sql = "INSERT INTO Supplier VALUES(?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getSupId());
        pstm.setObject(2, supplier.getTel());
        pstm.setObject(3, supplier.getName());

        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();
        while (resultSet.next()) {
            String supId = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String name = resultSet.getString(3);

            Supplier supplier = new Supplier(supId, name, tel);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE Sup_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET Name = ?, Phone = ? WHERE Sup_Id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getTel());
        pstm.setObject(3, supplier.getSupId());

        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE Sup_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Supplier supplier = null;

        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String name = resultSet.getString(3);

            supplier = new Supplier(supId, name, tel);
        }
        return supplier;
    }
}
