package lk.ijse.smcmanagesuite.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Order {
    private String orderId;
    private LocalDate date;
    private String price;
    private String cusPhone;
    private String cusName;

}
