package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemwithSupplierTm {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
    private String supName;
}
