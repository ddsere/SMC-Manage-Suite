package lk.ijse.smcmanagesuite.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ChangeAppointment {
    private Order order;
    private AppointmentStatus appoStatus;
}
