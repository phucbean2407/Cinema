package fa.training.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Builder
@Getter
@Setter
public class MovieDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull()
    @Positive
    private int lengthMinute;
    @NotNull()
    @Positive()
    private double rating;

    private CategoryDTO categoryDTO;


}
