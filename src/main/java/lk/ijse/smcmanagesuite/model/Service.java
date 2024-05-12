package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode

public class Service {
    private String serviceId;
    private String description;
    private String price;
    private String empId;

    @Override
    public String toString() {
        return "Service{" +
                ", serviceId='" + serviceId + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", empId='" + empId + '\'' +
                '}';
    }
}
