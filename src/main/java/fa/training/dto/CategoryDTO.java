package fa.training.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class CategoryDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
}
