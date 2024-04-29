package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class CustomerTm {
    private String name;
    private String address;
    private String Phone;

    @Override
    public String toString() {
        return "CustomerTm{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + Phone + '\'' +
                '}';
    }
}
