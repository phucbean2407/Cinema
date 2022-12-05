package fa.training.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class TheaterHallDTO {

    @NotNull
    private String name;
    private boolean isFull;

    private List<SeatDTO> seatDTO;

}
