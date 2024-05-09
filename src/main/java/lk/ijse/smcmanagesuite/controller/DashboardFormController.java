package lk.ijse.smcmanagesuite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane rootNode;

    public void initialize(){
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedTime = time.format(timeformatter);
        String formattedDate = date.format(dateformatter);

        lblTime.setText(formattedTime);
        lblDate.setText(formattedDate);
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) {

    }

    public void btnOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {

    }

    public void btnServiceOnAction(ActionEvent actionEvent) {

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {

    }
}
