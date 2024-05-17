package lk.ijse.smcmanagesuite.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
    private String itemId;
    private String description;
    private Double price;
    private String qty;
    private String supId;
}
