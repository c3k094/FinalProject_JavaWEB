package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.DTO.UserLoginDTO;
import PETVET.bg.petvet.model.DTO.UserRegisterDTO;
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
        //TODO: Implement validation for deactivated users.
        Optional<UserEntity> userOpt = userRepository.findByEmail(userLoginDTO.getEmail());
        if(userOpt.isEmpty()) {
            return false;
    }
        String enteredPass = userLoginDTO.getPassword();
        String actualPass = userOpt.get().getPassword();

        boolean isPasswordCorrect = passwordEncoder.matches(enteredPass, actualPass);

        if (isPasswordCorrect) {
            login(userOpt.get());

        }
        else {
            logout();
        }
        return isPasswordCorrect;
    }

    private void login(UserEntity userOpt) {
        currentUser.setLoggedIn(true).setName(userOpt.getFirstName() + " " + userOpt.getLastName());
    }

    public void logout() {
        currentUser.clear();
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        //TODO: Implement confirmation and validation of the password
        UserEntity newUser =
                new UserEntity().
                        setActive(true).
                        setEmail(userRegisterDTO.getEmail()).
                        setFirstName(userRegisterDTO.getFirstName()).
                        setLastName(userRegisterDTO.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        newUser = userRepository.save(newUser);
        login(newUser);
    }
}
