package fa.training.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String categoryDTO;
}
