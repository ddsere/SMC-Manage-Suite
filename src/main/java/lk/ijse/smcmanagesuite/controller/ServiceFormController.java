package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.smcmanagesuite.model.Item;
import lk.ijse.smcmanagesuite.model.ItemwithSupplier;
import lk.ijse.smcmanagesuite.model.ServicewithEmployee;
import lk.ijse.smcmanagesuite.model.tm.ItemwithSupplierTm;
import lk.ijse.smcmanagesuite.model.tm.ServicewithEmployeeTm;
import lk.ijse.smcmanagesuite.repository.ItemRepo;
import lk.ijse.smcmanagesuite.repository.ItemwithSupplierRepo;
import lk.ijse.smcmanagesuite.repository.ServicewithEmployeeRepo;
import lk.ijse.smcmanagesuite.repository.SupplierRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceFormController {

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private Label lblEmpName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ServicewithEmployeeTm> tblService;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    private List<ServicewithEmployee> servicewithEmployeeList = new ArrayList<>();

    public void initialize() {
        this.servicewithEmployeeList = getAllItems();
        setCellValueFactory();
        loadServiceTable();
        getEmpIds();
    }

    private List<ServicewithEmployee> getAllItems() {
        List<ServicewithEmployee> servicewithEmployeeList = null;
        try {
            servicewithEmployeeList = ServicewithEmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicewithEmployeeList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("ServiceId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
    }

    private void loadServiceTable() {
        ObservableList<ServicewithEmployeeTm> tmList = FXCollections.observableArrayList();

        for (ServicewithEmployee servicewithEmployee : servicewithEmployeeList) {
            ServicewithEmployeeTm servicewithEmployeeTm = new ServicewithEmployeeTm(
                    servicewithEmployee.getEmpId(),
                    servicewithEmployee.getDescription(),
                    servicewithEmployee.getPrice(),
                    servicewithEmployee.getEmpId(),
                    servicewithEmployee.getEmpName()
            );

            tmList.add(servicewithEmployeeTm);
        }
        tblService.setItems(tmList);
        ServicewithEmployeeTm selectedItem = (ServicewithEmployeeTm) tblService.getSelectionModel().getSelectedItem();
        //System.out.println("selectedItem = " + selectedItem);
    }

    private void getEmpIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ServicewithEmployeeRepo.getCodes();
            for (String id : idList) {
                obList.add(id);
            }

            cmbEmpId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        lblEmpName.setText("");
        cmbEmpId.getSelectionModel().clearSelection();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = ServicewithEmployeeRepo.delete(id);
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
        String id = txtId.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String empId = cmbEmpId.getValue();

        ServicewithEmployee servicewithEmployee = new ServicewithEmployee(id,description,price,empId);

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

    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
