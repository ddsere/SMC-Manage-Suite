package lk.ijse.smcmanagesuite.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ServiceCartTm {
    private String sId;
    private String sDesc;
    private Double sPrice;
    private JFXButton btnRemove;
}
