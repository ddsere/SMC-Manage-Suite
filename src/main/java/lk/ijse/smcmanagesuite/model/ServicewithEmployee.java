package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServicewithEmployee {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;
}
