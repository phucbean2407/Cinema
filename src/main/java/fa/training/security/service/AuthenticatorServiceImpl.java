package fa.training.security.service;

import fa.training.config.ModelMapperConfig;
import fa.training.dto.PeopleDTO;
import fa.training.dto.RoleDTO;
import fa.training.dto.UserDTO;
import fa.training.entity.People;
import fa.training.entity.Role;
import fa.training.entity.login.ERole;
import fa.training.entity.login.LoginRequest;
import fa.training.entity.login.SignupRequest;
import fa.training.entity.login.User;
import fa.training.repository.PeopleRepository;
import fa.training.repository.RoleRepository;
import fa.training.repository.UserRepository;
import fa.training.respone.JwtResponse;
import fa.training.respone.MessageResponse;
import fa.training.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthenticatorServiceImpl  implements AuthenticatorService{
    private final UserRepository userRepository;
    private final ModelMapperConfig modelMapperConfig;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final PeopleRepository peopleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticatorServiceImpl(UserRepository userRepository, ModelMapperConfig modelMapperConfig, PasswordEncoder encoder, RoleRepository roleRepository, PeopleRepository peopleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.modelMapperConfig = modelMapperConfig;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.peopleRepository = peopleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getPeopleDTO().getUserDTO().getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getPeopleDTO().getUserDTO().getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getPeopleDTO().getUserDTO().getUsername(),
                signUpRequest.getPeopleDTO().getUserDTO().getEmail(),encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = new HashSet<>();

        Set<RoleDTO> roleDTOS = signUpRequest.getPeopleDTO().getUserDTO().getRoleDTOs();
        for(RoleDTO role : roleDTOS) {
            String r = role.getName().name();
            strRoles.add(r);
        }

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        User newUser = userRepository.findByUsername(signUpRequest.getPeopleDTO().getUserDTO().getUsername())
                .orElseThrow(() -> new RuntimeException(""));
        UserDTO userDTO = new UserDTO();
        userDTO.setRoleDTOs(newUser.getRoles()
                .stream()
                .map(role -> modelMapperConfig.modelMapper().map(role, RoleDTO.class))
                .collect(Collectors.toSet()));
        userDTO.setUsername(newUser.getUsername());
        userDTO.setEmail(newUser.getEmail());

        People person = new People();
        person.setName(signUpRequest.getPeopleDTO().getName());
        person.setPhone(signUpRequest.getPeopleDTO().getPhone());
        person.setBirthday(signUpRequest.getPeopleDTO().getBirthday());
        person.setAddress(signUpRequest.getPeopleDTO().getAddress());
        person.setUser(user);
        peopleRepository.save(person);
        PeopleDTO peopleDTO = signUpRequest.getPeopleDTO();
        peopleDTO.setUserDTO(userDTO);
        return ResponseEntity.ok((peopleDTO));
    }
}
