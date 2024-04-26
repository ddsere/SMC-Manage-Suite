package lk.ijse.smcmanagesuite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.smcmanagesuite.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String user_id = txtUserId.getText();
        String name = txtUserName.getText();
        String password = txtPassword.getText();

        saveUser(user_id, name, password);
    }

    private void saveUser(String userId, String name, String password) {
        try{
            String sql ="INSERT INTO Users VALUES(?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1, userId);
            pstm.setObject(2, name);
            pstm.setObject(3, password);

            if (pstm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }
}
