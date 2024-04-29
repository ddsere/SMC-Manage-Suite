package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class Customer {
    private String name;
    private String address;
    private String tel;

    @Override
    public String toString() {
        return "Customer{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
