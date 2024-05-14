package lk.ijse.smcmanagesuite.model;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Appointment {
    private String appId;
    private Date date;
    private String cusPhone;
    private String servId;
    private String empId;
    private String timeSlot;
}
