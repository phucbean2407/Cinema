package fa.training.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class TimeDTO {
    @NotNull
    @Size(max = 5, min = 5, message = "Time Type: hh:mm")
    private String time;
}
