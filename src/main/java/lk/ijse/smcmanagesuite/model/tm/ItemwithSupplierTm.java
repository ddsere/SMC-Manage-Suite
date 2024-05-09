package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class ItemwithSupplierTm {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
    private String supName;

    @Override
    public String toString() {
        return "Supplier{" +
                ", itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", supId='" + supId + '\'' +
                ", supName='" + supName + '\'' +
                '}';
    }
}
