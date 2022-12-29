package fa.training.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Builder
@Getter
@Setter
public class PeopleDTO {

    @NotNull(message = "Empty Name")
    private String name;
    @NotNull(message = "Empty BirthDay")
    private Date birthday;
    @NotNull(message = "Empty Address")
    @Size(min = 6, message = "Address have more 6 characters")
    private String address;
    @NotNull(message = "Empty Telephone")
    @NotBlank(message = "Telephone number must not have blank")
    @Size(min = 10, max = 10, message = "Vietnamese Telephone number must have 10 numbers")
    private String phone;
    @NotNull
    private UserDTO userDTO;
}
