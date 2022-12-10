package fa.training.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class HallDTO {

    @NotNull
    private String name;

}
