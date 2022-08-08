package PETVET.bg.petvet.util;

import PETVET.bg.petvet.model.entity.AddressEntity;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;
import PETVET.bg.petvet.repository.AddressRepository;
import PETVET.bg.petvet.repository.OwnerRepository;
import PETVET.bg.petvet.repository.UserRepository;
import PETVET.bg.petvet.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private AddressRepository addressRepository;
    private OwnerRepository ownerRepository;

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, AddressRepository addressRepository, OwnerRepository ownerRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.addressRepository = addressRepository;
        this.ownerRepository = ownerRepository;
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
                setPassword("topsecret").
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
                setPassword("topsecret").
                setFirstName("User").
                setLastName("Userov").
                setActive(true);

        return userRepository.save(user);
    }
    public AddressEntity createTestAddress(String country, String city, String street) {
        var addressEntity = (AddressEntity) new AddressEntity()
                .setCountry(country)
                .setCity(city)
                .setStreet(street)
                .setPostcode("12345")
                .setId(1L);
        return addressRepository.save(addressEntity);
    }

    public OwnerEntity createTestOwner(String email, String phone, AddressEntity address) {
        var ownerEntity = (OwnerEntity) new OwnerEntity()
                .setEmail(email)
                .setPhoneNumber(phone)
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setAddress(address)
                .setId(1L);
       return ownerRepository.save(ownerEntity);
    }

    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

}
