package fa.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChooseOrder {
    @NotNull
    private TicketDTO ticketDTO;

    @NotNull
    private List<String> numberSeat;
}
