package fa.training.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Builder
@Getter
@Setter
public class HallDTO {

    @NotNull
    private String name;

}
