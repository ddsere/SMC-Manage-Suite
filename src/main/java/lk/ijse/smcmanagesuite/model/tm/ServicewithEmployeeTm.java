package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServicewithEmployeeTm {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;
}
