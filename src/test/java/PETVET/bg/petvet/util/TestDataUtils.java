package PETVET.bg.petvet.util;

import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;
import PETVET.bg.petvet.repository.UserRepository;
import PETVET.bg.petvet.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(moderatorRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Admin").
                setLastName("Adminov").
                setActive(true).
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov").
                setActive(true);

        return userRepository.save(user);
    }

    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

}
