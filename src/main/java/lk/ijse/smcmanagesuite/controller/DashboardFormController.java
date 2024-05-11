package lk.ijse.smcmanagesuite.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane dashContentPane;

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
}
