package lk.ijse.smcmanagesuite.model.tm;

import java.util.Date;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderTm {
    private String orderId;
    private Date date;
    private String cusName;
    private String cusPhone;
    private double amount;
}
