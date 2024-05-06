package lk.ijse.smcmanagesuite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {

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
