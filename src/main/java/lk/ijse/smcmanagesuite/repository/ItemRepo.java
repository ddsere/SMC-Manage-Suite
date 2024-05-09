package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM Item";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);

            Item item = new Item(itemId,description,price,qty,supId);
            itemList.add(item);
        }
        return itemList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Item WHERE Item_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO Item VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getPrice());
        pstm.setObject(4, item.getQty());
        pstm.setObject(5, item.getSupId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE Item SET Sup_Id = ?, Description = ?, Price = ?, Qty = ?, WHERE Item_Id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getPrice());
        pstm.setObject(4, item.getQty());
        pstm.setObject(5, item.getSupId());

        return pstm.executeUpdate() > 0;
    }

    public static Item searchById(String code) throws SQLException {
        String sql = "SELECT * FROM Item WHERE Item_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, code);
        ResultSet resultSet = pstm.executeQuery();

        Item item = null;

        if (resultSet.next()) {
            String itemCode = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);

            item = new Item(itemCode, description, price, qty, supId);
        }
        return item;
    }
}
