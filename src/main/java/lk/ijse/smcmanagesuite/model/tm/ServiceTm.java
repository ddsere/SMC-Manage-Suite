package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode

public class ServiceTm {
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
