package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentTm {
    private String appId;
    private String cusPhone;
    private String sName;
    private Date date;
    private String timeSlot;
    private String empName;
}
