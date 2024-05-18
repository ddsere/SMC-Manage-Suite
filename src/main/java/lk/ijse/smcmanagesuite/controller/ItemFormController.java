package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemwithSupplier;
import lk.ijse.smcmanagesuite.model.Supplier;
import lk.ijse.smcmanagesuite.model.tm.ItemwithSupplierTm;
import lk.ijse.smcmanagesuite.repository.ItemRepo;
import lk.ijse.smcmanagesuite.repository.ItemwithSupplierRepo;
import lk.ijse.smcmanagesuite.repository.SupplierRepo;
import lk.ijse.smcmanagesuite.util.Regex;
import lk.ijse.smcmanagesuite.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemFormController {

    @FXML
    private JFXComboBox<String> cmbSupId;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblSupName;

    @FXML
    private TableView<ItemwithSupplierTm> tblItem;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    private List<ItemwithSupplier> itemwithSupplierList = new ArrayList<>();

    public void initialize() {
        this.itemwithSupplierList = getAllItems();
        setCellValueFactory();
        loadItemTable();
        getSupIds();
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("SupName"));
    }

    private void loadItemTable() {
        ObservableList<ItemwithSupplierTm> tmList = FXCollections.observableArrayList();

        for (ItemwithSupplier itemwithSupplier : itemwithSupplierList) {
            ItemwithSupplierTm itemwithSupplierTm = new ItemwithSupplierTm(
                    itemwithSupplier.getItemId(),
                    itemwithSupplier.getDescription(),
                    itemwithSupplier.getPrice(),
                    itemwithSupplier.getQty(),
                    itemwithSupplier.getSupId(),
                    itemwithSupplier.getSupName()
            );

            tmList.add(itemwithSupplierTm);
        }
        tblItem.setItems(tmList);
        ItemwithSupplierTm selectedItem = (ItemwithSupplierTm) tblItem.getSelectionModel().getSelectedItem();
        //System.out.println("selectedItem = " + selectedItem);
    }

    private List<ItemwithSupplier> getAllItems() {
        List<ItemwithSupplier> itemList = null;
        try {
            itemList = ItemwithSupplierRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
        lblSupName.setText("");
        cmbSupId.getSelectionModel().clearSelection();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCode.getText();

        try {
            boolean isDeleted = ItemRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
        initialize();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValid()) {
            String code = txtCode.getText();
            String description = txtDescription.getText();
            Double price = Double.valueOf(txtUnitPrice.getText());
            String qty = txtQtyOnHand.getText();
            String supId = cmbSupId.getValue();

            Item item = new Item(code, description, price, qty, supId);

            try {
                boolean isSaved = ItemRepo.save(item);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValid()) {
            String code = txtCode.getText();
            String description = txtDescription.getText();
            Double price = Double.valueOf(txtUnitPrice.getText());
            String qty = txtQtyOnHand.getText();
            String supId = cmbSupId.getValue();

            Item item = new Item(code, description, price, qty, supId);

            try {
                boolean isUpdated = ItemRepo.update(item);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Updated!").show();
                }
            } catch (SQLException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    private void getSupIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = SupplierRepo.getCodes();
            for (String id : idList) {
                obList.add(id);
            }

            cmbSupId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {
        String supId = cmbSupId.getValue();

        try {
            Supplier supplier = SupplierRepo.searchById(supId);
            if (supplier != null) {
                lblSupName.setText(supplier.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            ItemwithSupplier itemwithSupplier = ItemwithSupplierRepo.searchById(code);

            if (itemwithSupplier != null) {
                txtCode.setText(itemwithSupplier.getItemId());
                txtDescription.setText(itemwithSupplier.getDescription());
                txtUnitPrice.setText(itemwithSupplier.getPrice());
                txtQtyOnHand.setText(itemwithSupplier.getQty());
                lblSupName.setText(itemwithSupplier.getSupName());
                cmbSupId.setValue(itemwithSupplier.getSupId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.IID,txtCode)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtDescription)) return false;
        if (!Regex.setTextColor(TextFields.QTY,txtQtyOnHand)) return false;
        if (!Regex.setTextColor(TextFields.PRICE,txtUnitPrice)) return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IID,txtCode);
    }

    public void txtDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtDescription);
    }

    public void txtPriceCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.QTY,txtQtyOnHand);
    }

    public void txtQtyCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PRICE,txtUnitPrice);
    }
}
