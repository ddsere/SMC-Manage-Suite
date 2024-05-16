package lk.ijse.smcmanagesuite.model;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Appointment {
    private String appId;
    private LocalDate date;
    private String cusPhone;
    private String servId;
    private String empId;
    private String timeSlot;
    private String price;
}
