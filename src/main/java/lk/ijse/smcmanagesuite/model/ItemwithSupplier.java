package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemwithSupplier {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
    private String supName;
}
