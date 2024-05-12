package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode

public class Employee {
    private String id;
    private String name;
    private String salary;
    private String address;
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", salary='" + address + '\'' +
                ", salary='" + phone + '\'' +
                '}';
    }
}
