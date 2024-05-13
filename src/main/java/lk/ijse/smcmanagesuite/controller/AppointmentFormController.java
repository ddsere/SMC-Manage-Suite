package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AppointmentFormController {

    @FXML
    private JFXComboBox<?> cmbCusPhone;

    @FXML
    private JFXComboBox<?> cmbEmpId;

    @FXML
    private JFXComboBox<?> cmbServId;

    @FXML
    private JFXComboBox<?> cmbStatus;

    @FXML
    private JFXComboBox<?> cmbTimeSlot;

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
    private TableColumn<?, ?> colStatus;

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
    private TextField txtDate;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

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

}
