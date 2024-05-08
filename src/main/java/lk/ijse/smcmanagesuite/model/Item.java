package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode

public class Item {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;

    @Override
    public String toString() {
        return "Supplier{" +
                ", itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", supId='" + supId + '\'' +
                '}';
    }
}
