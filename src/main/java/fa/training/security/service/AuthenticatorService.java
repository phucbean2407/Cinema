package fa.training.security.service;

import fa.training.entity.login.LoginRequest;
import fa.training.entity.login.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface AuthenticatorService {
    ResponseEntity<?> authenticateUser( LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
