package lk.ijse.smcmanagesuite.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemCartTm {
    private String itemId;
    private String itemDesc;
    private Double itemUnitPrice;
    private int itemQty;
    private Double itemTotal;
    private JFXButton btnRemove;
}
