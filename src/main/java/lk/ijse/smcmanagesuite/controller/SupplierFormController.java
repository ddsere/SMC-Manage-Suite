package lk.ijse.smcmanagesuite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.smcmanagesuite.model.Customer;
import lk.ijse.smcmanagesuite.model.Supplier;
import lk.ijse.smcmanagesuite.model.tm.SupplierTm;
import lk.ijse.smcmanagesuite.repository.CustomerRepo;
import lk.ijse.smcmanagesuite.repository.SupplierRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    private List<Supplier> supplierList = new ArrayList<>();

    public void initialize() {
        this.supplierList = getAllSuppliers();
        setCellValueFactory();
        loadSupplierTable();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    private void loadSupplierTable() {
        ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();

        for (Supplier supplier : supplierList) {
            SupplierTm supplierTm = new SupplierTm(
                    supplier.getSupId(),
                    supplier.getName(),
                    supplier.getTel()
            );

            tmList.add(supplierTm);
        }
        tblSupplier.setItems(tmList);
        SupplierTm selectedItem = (SupplierTm) tblSupplier.getSelectionModel().getSelectedItem();
        //System.out.println("selectedItem = " + selectedItem);
    }

    private List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = null;
        try {
            supplierList = SupplierRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
        initialize();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();

        Supplier supplier = new Supplier(id, name, tel);

        try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = txtId.getText();
        String address = txtName.getText();
        String tel = txtTel.getText();

        Supplier supplier = new Supplier(name, address, tel);

        try {
            boolean isUpdated = SupplierRepo.update(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Supplier supplier = SupplierRepo.searchById(id);

            if (supplier != null) {
                txtTel.setText(supplier.getTel());
                txtName.setText(supplier.getName());
                txtId.setText(supplier.getSupId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
