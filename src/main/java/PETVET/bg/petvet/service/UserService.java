package PETVET.bg.petvet.service;

import PETVET.bg.petvet.config.UserMapper;
import PETVET.bg.petvet.model.dto.UserLoginDTO;
import PETVET.bg.petvet.model.dto.UserRegisterDTO;
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
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
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
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        newUser.setActive(true);
        this.userRepository.save(newUser);
        login(newUser);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
