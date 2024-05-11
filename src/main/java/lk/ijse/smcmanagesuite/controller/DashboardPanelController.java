package lk.ijse.smcmanagesuite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardPanelController implements Initializable {
    public BorderPane rootNode;
    @FXML
    private StackPane dashContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            dashContainer.getChildren().removeAll();
            dashContainer.getChildren().setAll(fxml);
        }
        catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/SupplierForm.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) {

    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/EmployeeForm.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) {

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }
}
