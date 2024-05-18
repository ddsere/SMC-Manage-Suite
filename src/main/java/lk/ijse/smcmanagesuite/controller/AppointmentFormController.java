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
import lk.ijse.smcmanagesuite.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentFormController {

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXComboBox<String> cmbServId;

    @FXML
    private JFXComboBox<String> cmbTimeSlot;

    @FXML
    private TableColumn<?, ?> colAppId;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colServName;

    @FXML
    private TableColumn<?, ?> colTimeSlot;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblServiceName;

    @FXML
    private TableView<AppointmentTm> tblAppointment;

    @FXML
    private TextField txtAppId;

    @FXML
    private TextField txtCusPhone;

    @FXML
    private DatePicker txtDate;

    private List<AppointmentDetails> appointmentList = new ArrayList<>();
    private double price;

    public void initialize() {
        this.appointmentList = getAllItems();
        setCellValueFactory();
        loadAppTable();
        getTimeSlot();
        getServiceId();
        getEmployeeId();
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

    private void setCellValueFactory() {
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colServName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
    }

    private void loadAppTable() {
        ObservableList<AppointmentTm> tmList = FXCollections.observableArrayList();

        for (AppointmentDetails appointmentDetails : appointmentList) {
            AppointmentTm appointmentTm = new AppointmentTm(
                    appointmentDetails.getAppId(),
                    appointmentDetails.getCusName(),
                    appointmentDetails.getSName(),
                    appointmentDetails.getDate(),
                    appointmentDetails.getTimeSlot(),
                    appointmentDetails.getEmpName()
            );
            tmList.add(appointmentTm);
        }
        tblAppointment.setItems(tmList);
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


    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = EmployeeRepo.getCodes();
            for (String id : idList) {
                obList.add(id);
            }

            cmbEmpId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getServiceId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ServiceRepo.getIds();
            for (String id : idList) {
                obList.add(id);
            }

            cmbServId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtAppId.setText("");
        txtDate.setValue(null);
        txtCusPhone.setText("");
        lblServiceName.setText("");
        lblEmpName.setText("");
        lblCusName.setText("");
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
        String cusPhone = txtCusPhone.getText();
        String servId = cmbServId.getValue();
        String empId = cmbEmpId.getValue();
        String ts = cmbTimeSlot.getValue();


        try {
            price = ServiceRepo.getPrice(servId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Appointment appointment = new Appointment(appId, date, cusPhone, servId, empId, ts, price);
        System.out.println(appointment.toString());

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
        String id = txtAppId.getText();

        try {
            boolean isDeleted = AppointmentRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "A Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
        initialize();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String appId = txtAppId.getText();
        LocalDate date = txtDate.getValue();
        String cusPhone = txtCusPhone.getText();
        String servId = cmbServId.getValue();
        String empId = cmbEmpId.getValue();
        String ts = cmbTimeSlot.getValue();

        Appointment appointment = new Appointment(appId, date, cusPhone, servId, empId, ts, price);

        try {
            boolean isUpdated = AppointmentRepo.update(appointment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    public void btnCompleteOnAction(ActionEvent actionEvent) {
        String appId = txtAppId.getText();
        String servId = cmbServId.getValue();
        double price;

        try {
            price = ServiceRepo.getPrice(servId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String cusPhone = txtCusPhone.getText();
        String cusName = lblCusName.getText();
        Date date = Date.valueOf(LocalDate.now());

        String orderId = loadNextOrderId();
        var order = new Order(orderId, date, price, cusPhone, cusName);

        String status = "Completed";
        AppointmentStatus appointmentStatus = new AppointmentStatus(status, appId);

        ChangeAppointment po = new ChangeAppointment(order, appointmentStatus);
        System.out.println(po.toString());
        try {
            boolean isPlaced = ChangeAppointmentRepo.changeAppointment(po);
            System.out.println(isPlaced);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "order not placed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String loadNextOrderId() {
        try {
            String currentId = OrderRepo.currentId();
            String nextId = nextId(currentId);

            String orderId = (nextId);
            return orderId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("D");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "D" + String.format("%03d", id);
        } else {
            return "D001";
        }
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        String empId = cmbEmpId.getValue();

        try {
            Employee employee = EmployeeRepo.searchById(empId);
            if (employee != null) {
                lblEmpName.setText(employee.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbServIdOnAction(ActionEvent event) {
        String sId = cmbServId.getValue();

        try {
            Service service = ServiceRepo.searchById(sId);
            if (service != null) {
                lblServiceName.setText(service.getDescription());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String cusPhone = txtCusPhone.getText();

        try {
            Customer customer = CustomerRepo.searchById(cusPhone);
            if (customer != null) {
                lblCusName.setText(customer.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            AppointmentSearch appointmentSearch = AppointmentDetailsRepo.searchById(cusPhone);

            if (appointmentSearch != null) {
                txtAppId.setText(appointmentSearch.getAppId());
                txtDate.setValue(appointmentSearch.getDate());
                cmbServId.setValue(appointmentSearch.getSId());
                cmbEmpId.setValue(appointmentSearch.getEmpId());
                cmbTimeSlot.setValue(appointmentSearch.getTs());
                lblCusName.setText(appointmentSearch.getCusName());
                lblEmpName.setText(appointmentSearch.getEmpName());
                lblServiceName.setText(appointmentSearch.getSName());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
