package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.Supplier;
import lk.ijse.smcmanagesuite.model.tm.ItemTm;
import lk.ijse.smcmanagesuite.model.tm.SupplierTm;
import lk.ijse.smcmanagesuite.repository.ItemRepo;
import lk.ijse.smcmanagesuite.repository.SupplierRepo;

import java.io.IOException;
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
    private AnchorPane root;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    private List<Item> itemList = new ArrayList<>();

    public void initialize() {
        this.itemList = getAllItems();
        setCellValueFactory();
        loadItemTable();
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        for (Item item : itemList) {
            ItemTm itemTm = new ItemTm(
                    item.getItemId(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupId()
            );

            tmList.add(itemTm);
        }
        tblItem.setItems(tmList);
        ItemTm selectedItem = (ItemTm) tblItem.getSelectionModel().getSelectedItem();
        //System.out.println("selectedItem = " + selectedItem);
    }

    private List<Item> getAllItems() {
        List<Item> itemList = null;
        try {
            itemList = ItemRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
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
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String price = txtUnitPrice.getText();
        String qty = txtQtyOnHand.getText();
        String supId = cmbSupId.getValue();

        Item item = new Item(code,description,price,qty,supId);

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

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String price = txtUnitPrice.getText();
        String qty = txtQtyOnHand.getText();
        String supId = cmbSupId.getValue();

        Item item = new Item(code,description,price,qty,supId);

        try {
            boolean isUpdated = ItemRepo.update(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {
        String supId = cmbSupId.getValue();

        try {
            Supplier supplier = SupplierRepo.searchById(supId);

            lblSupName.setText(supplier.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            Item item = ItemRepo.searchById(code);

            if (item != null) {
                txtCode.setText(item.getItemId());
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(item.getPrice());
                txtQtyOnHand.setText(item.getQty());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
