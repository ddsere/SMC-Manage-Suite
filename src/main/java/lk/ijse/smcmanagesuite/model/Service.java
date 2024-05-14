package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Service {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
}
