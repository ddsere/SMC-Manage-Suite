package lk.ijse.smcmanagesuite.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PlaceOrder {
    private Order order;
    private AppointmentStatus appoStatus;
}
