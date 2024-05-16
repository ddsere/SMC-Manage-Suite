package lk.ijse.smcmanagesuite.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentSearch {
    private String appId;
    private LocalDate date;
    private String sId;
    private String empId;
    private String ts;
    private String cusName;
    private String empName;
    private String sName;

}
