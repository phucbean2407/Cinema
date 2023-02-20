package fa.training.service;

import fa.training.model.request.LoginRequest;
import fa.training.model.request.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface AuthenticatorService {
    ResponseEntity<?> authenticateUser( LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
