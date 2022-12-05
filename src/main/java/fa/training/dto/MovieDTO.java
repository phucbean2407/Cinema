package fa.training.dto;



import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class MovieDTO {

    @NotNull(message = "Empty Movie Name")
    private String name;
    @NotNull(message = "Empty Description")
    private String description;
    @NotNull()
    @Positive(message = "Must > 0")
    private int lengthMinute;
    @NotNull()
    @Positive()
    private double rating;

    private CategoryDTO categoryDTO;


}
