package lk.ijse.smcmanagesuite.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemTm {
    private String itemId;
    private String description;
    private Double price;
    private int qty;
    private JFXButton btnItemCartRemove;
}
