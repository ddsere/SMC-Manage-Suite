package lk.ijse.smcmanagesuite.model;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentDetails {

    private String appId;
    private String cusPhone;
    private String sName;
    private Date date;
    private String timeSlot;
    private String empName;
    private String status;
}
