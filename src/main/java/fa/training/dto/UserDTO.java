package fa.training.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
public class UserDTO {

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<RoleDTO> roleDTOs = new HashSet<>();
}
