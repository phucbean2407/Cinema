package fa.training.entity.login;

import fa.training.dto.PeopleDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
public class SignupRequest {

    @NotNull
    private PeopleDTO peopleDTO;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;




}