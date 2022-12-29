package fa.training.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
@Builder
@Getter
public class ChooseOrder {
    @NotNull
    private TicketDTO ticketDTO;

    @NotNull
    private List<String> numberSeat;
}
