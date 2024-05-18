package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.util.Regex;
import lk.ijse.smcmanagesuite.util.TextFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFormController {

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (isValid()) {
            String user_id = txtUserId.getText();
            String name = txtUserName.getText();
            String password = txtPassword.getText();

            saveUser(user_id, name, password);

        }
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

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.UID,txtUserId)) return false;
        if (!Regex.setTextColor(TextFields.PW,txtPassword)) return false;
        return true;
    }

    public void txtPwCheckOnAciton(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PW,txtPassword);
    }

    public void txtUserNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.UNAME,txtUserName);
    }

    public void txtUserIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.UID,txtUserId);
    }
}
