package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class SupplierTm {
    private String supId;
    private String name;
    private String Phone;

    @Override
    public String toString() {
        return "Supplier{" +
                ", SupId='" + supId + '\'' +
                ", Name='" + name + '\'' +
                ", Tel='" + Phone + '\'' +
                '}';
    }
}
