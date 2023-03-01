package fa.training.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
}
