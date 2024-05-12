package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemwithSupplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemwithSupplierRepo {
    public static List<ItemwithSupplier> getAll() throws SQLException {
        String sql = "SELECT i.*, s.Name AS SupplierName FROM Item i INNER JOIN Supplier s ON i.Sup_Id = s.Sup_Id;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ItemwithSupplier> itemwithSupplierList = new ArrayList<>();
        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);
            String supName = resultSet.getString(6);

            ItemwithSupplier itemwithSupplier = new ItemwithSupplier(itemId,description,price,qty,supId,supName);
            itemwithSupplierList.add(itemwithSupplier);
        }
        return itemwithSupplierList;
    }

    public static ItemwithSupplier searchById(String code) throws SQLException {
        String sql = "SELECT i.*, s.Name AS SupplierName FROM Item i INNER JOIN Supplier s ON i.Sup_Id = s.Sup_Id WHERE Item_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, code);
        ResultSet resultSet = pstm.executeQuery();

        ItemwithSupplier itemwithSupplier = null;

        if (resultSet.next()) {
            String itemCode = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);
            String supName = resultSet.getString(6);

            itemwithSupplier = new ItemwithSupplier(itemCode, description, price, qty, supId, supName);
        }
        return itemwithSupplier;
    }
}
