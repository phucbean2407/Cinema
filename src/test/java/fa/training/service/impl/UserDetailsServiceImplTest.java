package fa.training.service.impl;

import fa.training.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {


    User user;
    @BeforeEach
    void setUp() {
        String username = "phucbean2407";
        String email = "phucbean2407@gmail.com";
        user = new User(username,email,"123456");
    }
    void loadUserByUsername() {

    }
}