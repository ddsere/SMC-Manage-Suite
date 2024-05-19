package lk.ijse.smcmanagesuite.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.smcmanagesuite.repository.AppointmentRepo;
import lk.ijse.smcmanagesuite.repository.CustomerRepo;
import lk.ijse.smcmanagesuite.repository.OrderRepo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {

    @FXML
    private Label lblBooking;

    @FXML
    private Label lblCusCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblTime;

    private int appCount;
    private int salesCount;
    private int cusCount;

    private volatile boolean stop = false;

    public void initialize(){
        timeNow();

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(dateformatter);
        lblDate.setText(formattedDate);

        try {
            appCount = AppointmentRepo.getAppCount();
            salesCount = OrderRepo.getOrderCount();
            cusCount = CustomerRepo.getCusCount();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        setAppCount(appCount);
        setSalesCount(salesCount);
        setCusCount(cusCount);
    }

    private void setCusCount(int cusCount) {
        lblCusCount.setText(String.valueOf(cusCount));
    }

    private void setSalesCount(int salesCount) {
        lblSales.setText(String.valueOf(salesCount));
    }

    private void setAppCount(int appCount) {
        lblBooking.setText(String.valueOf(appCount));
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
