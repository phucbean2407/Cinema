package fa.training.dto;


import fa.training.entity.Time;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Builder
@Getter
@Setter
public class MovieShowTimeDTO {

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    private Time time;
    @NotNull
    private MovieDTO movieDTO;
    @NotNull
    private HallDTO hallDTO;
    private Set<SeatDTO> seatDTOS;

}
