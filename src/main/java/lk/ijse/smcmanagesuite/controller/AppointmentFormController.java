package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.smcmanagesuite.model.*;
import lk.ijse.smcmanagesuite.model.tm.AppointmentTm;
import lk.ijse.smcmanagesuite.model.tm.ItemwithSupplierTm;
import lk.ijse.smcmanagesuite.repository.*;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentFormController {

    @FXML
    private JFXComboBox<String> cmbCusPhone;

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXComboBox<String> cmbServId;

    @FXML
    private JFXComboBox<String> cmbTimeSlot;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAppId;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colServName;

    @FXML
    private TableColumn<?, ?> colTimeSlot;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblServiceName;

    @FXML
    private TableView<?> tblAppointment;

    @FXML
    private TextField txtAppId;

    @FXML
    private DatePicker txtDate;

    private List<AppointmentDetails> appointmentList = new ArrayList<>();

    public void initialize() throws SQLException {
        this.appointmentList = getAllItems();
        setCellValueFactory();
        loadAppTable();
        getTimeSlot();
        getCusId();
        getServiceIds();
        getEmployeeId();
    }


    private void getTimeSlot() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {

            obList.add("9:00am - 10:00am");
            obList.add("10:00am - 11:00am");
            obList.add("11:00am - 12:00pm");
            obList.add("1:00pm - 2:00pm");
            obList.add("2:00pm - 3:00pm");
            cmbTimeSlot.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getCusId() {

    }

    private void getEmployeeId() {

    }

    private void getServiceIds() {

    }

    private void loadAppTable() throws SQLException {
        ObservableList<AppointmentTm> tmList = FXCollections.observableArrayList();

        for (AppointmentDetails appointmentDetails : appointmentList) {
            AppointmentTm appointmentTm = new AppointmentTm(
                    appointmentDetails.getAppId(),
                    appointmentDetails.getCusPhone(),
                    appointmentDetails.getSName(),
                    appointmentDetails.getDate(),
                    appointmentDetails.getTimeSlot(),
                    appointmentDetails.getEmpName()
            );

            tmList.add(appointmentTm);
        }
    }

    private void setCellValueFactory() {
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colServName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    }

    private List<AppointmentDetails> getAllItems() {
        List<AppointmentDetails> appointmentList = null;
        try {
            appointmentList = AppointmentDetailsRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    private void clearFields() {
        txtAppId.setText("");
        txtDate.setValue(null);
        cmbCusPhone.getSelectionModel().clearSelection();
        cmbEmpId.getSelectionModel().clearSelection();
        cmbServId.getSelectionModel().clearSelection();
        cmbTimeSlot.getSelectionModel().clearSelection();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String appId = txtAppId.getText();
        LocalDate date = txtDate.getValue();
        String cusPhone = cmbCusPhone.getValue();
        String servId = cmbServId.getValue();
        String empId = cmbEmpId.getValue();
        String ts = cmbTimeSlot.getValue();

        Appointment appointment = new Appointment(appId, date, cusPhone, servId, empId, ts);

        try {
            boolean isSaved = AppointmentRepo.save(appointment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCusPhoneOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbServIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTimeSlotOnAction(ActionEvent event) {

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {

    }
}
