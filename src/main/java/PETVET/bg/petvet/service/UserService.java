package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.DTO.UserLoginDTO;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.user.CurrentUser;
import PETVET.bg.petvet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(userLoginDTO.getEmail());
        if(userOpt.isEmpty()) {
            return false;
    }
        String enteredPass = userLoginDTO.getPassword();
        String actualPass = userOpt.get().getPassword();

        boolean isPasswordCorrect = passwordEncoder.matches(enteredPass, actualPass);

        if (isPasswordCorrect) {
            currentUser.setLoggedIn(true).setName(userOpt.get().getFirstName() + " " + userOpt.get().getLastName());

        }
        else {
            logout();
        }
        return isPasswordCorrect;
    }

    public void logout() {
        currentUser.clear();
    }
}
