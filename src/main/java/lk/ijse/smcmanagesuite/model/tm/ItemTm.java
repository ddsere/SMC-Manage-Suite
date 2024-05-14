package lk.ijse.smcmanagesuite.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemTm {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
}
