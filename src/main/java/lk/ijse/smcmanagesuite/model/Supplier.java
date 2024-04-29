package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class Supplier {
    private String supId;
    private String name;
    private String tel;

    @Override
    public String toString() {
        return "Supplier{" +
                ", supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
