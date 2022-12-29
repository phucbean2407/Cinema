package fa.training.entity.login;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Builder
@Getter
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}