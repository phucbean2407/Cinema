package fa.training.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class ChooseOrder {
    @NotNull
    private TicketDTO ticketDTO;

    @NotNull
    private List<String> numberSeat;
}
