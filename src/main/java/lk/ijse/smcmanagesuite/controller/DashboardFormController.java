package lk.ijse.smcmanagesuite.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    private volatile boolean stop = false;

    public void initialize(){
        timeNow();

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(dateformatter);
        lblDate.setText(formattedDate);
    }

    public void timeNow(){
        Thread thread = new Thread(()->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
