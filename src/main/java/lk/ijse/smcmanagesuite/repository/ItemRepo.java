package lk.ijse.smcmanagesuite.repository;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemQty;
import lk.ijse.smcmanagesuite.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
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
        String sql = "UPDATE Item SET Sup_Id = ?, Description = ?, Price = ?, Qty = ? WHERE Item_Id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, item.getSupId());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getPrice());
        pstm.setObject(4, item.getQty());
        pstm.setObject(5, item.getItemId());

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
            Double price = Double.valueOf(resultSet.getString(3));
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);

            item = new Item(itemCode, description, price, qty, supId);
        }
        return item;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT Item_Id FROM Item";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static boolean updateQty(List<ItemQty> itemQties) throws SQLException {
        for (ItemQty od : itemQties) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }
    public static boolean updateQty(ItemQty od) throws SQLException {
        String sql = "UPDATE item SET Qty = Qty - ? WHERE Item_Id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, od.getQty());
        pstm.setString(2, od.getItemCode());

        return pstm.executeUpdate() > 0;
    }
}
