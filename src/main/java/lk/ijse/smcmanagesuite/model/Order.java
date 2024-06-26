package lk.ijse.smcmanagesuite.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Order {
    private String orderId;
    private Date date;
    private double amount;
    private String cusPhone;
    private String cusName;

}
