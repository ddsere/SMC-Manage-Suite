package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode

public class ServicewithEmployee {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;

    @Override
    public String toString() {
        return "Service{" +
                ", serviceId='" + serviceId + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", empId='" + empId + '\'' +
                ", empId='" + empName + '\'' +
                '}';
    }
}
