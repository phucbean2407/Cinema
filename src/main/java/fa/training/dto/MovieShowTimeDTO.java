package fa.training.dto;


import fa.training.entity.Time;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;



@Data
public class MovieShowTimeDTO {

    @NotNull
    private Date date;
    private Time time;
    private MovieDTO movieDTO;
    private TheaterHallDTO theaterHallDTO;

}
