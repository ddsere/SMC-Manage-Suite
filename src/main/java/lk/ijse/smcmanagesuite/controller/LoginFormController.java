package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.util.Regex;
import lk.ijse.smcmanagesuite.util.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    public AnchorPane rootNode;

    @FXML
    public JFXPasswordField txtPassword;

    @FXML
    public JFXTextField txtUserId;


    @FXML
    public void btnLoginOnAction(ActionEvent event) throws IOException {
        if (isValid()){
            String userId = txtUserId.getText();
            String pw = txtPassword.getText();

            try {
                checkCredential(userId, pw);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT User_Id, Password FROM users WHERE User_Id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString(2);

            if(dbPw.equals(pw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "User Id not found!").show();
        }
    }

    void navigateToTheDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardPanel.fxml"));
        BorderPane dashboardPane = loader.load();

        rootNode.getChildren().clear();

        rootNode.getChildren().add(dashboardPane);
    }

    @FXML
    public void linkRegistrationOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/RegistrationForm.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");
        stage.show();
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.UID,txtUserId)) return false;
        if (!Regex.setTextColor(TextFields.PW,txtPassword)) return false;
        return true;
    }


    public void txtUsernmeCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.UID,txtUserId);

    }

    public void txtPwCheckOnAciton(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PW,txtPassword);

    }
}


