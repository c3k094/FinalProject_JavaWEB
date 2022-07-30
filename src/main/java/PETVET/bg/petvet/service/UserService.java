package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.UserEditDTO;
import PETVET.bg.petvet.model.dto.UserRegisterDTO;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;
import PETVET.bg.petvet.model.mapper.UserMapper;
import PETVET.bg.petvet.repository.UserRepository;
import PETVET.bg.petvet.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDetailsService appUserDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private String adminPass;
    private UserMapper userMapper;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService appUserDetailsService,
                       @Value("${app.default.admin.password}") String adminPass, UserMapper userMapper, ModelMapper modelMapper)  {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.adminPass = adminPass;
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
    }

    public Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of());
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setUserRoles(roles).
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setPassword(passwordEncoder.encode(adminPass));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {
        UserEntity moderator = new UserEntity().
                setUserRoles(roles).
                setFirstName("Moderator").
                setLastName("Moderatorov").
                setEmail("moderator@example.com").
                setPassword(passwordEncoder.encode(adminPass));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {
        UserEntity user = new UserEntity().
                setUserRoles(roles).
                setFirstName("User").
                setLastName("Userov").
                setEmail("user@example.com").
                setPassword(passwordEncoder.encode(adminPass));

        userRepository.save(user);
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        UserEntity newUser =
                userMapper.userDtoToUserEntity(userRegisterDTO);

        userRepository.save(newUser);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    private void updateAuthentication(UserEntity newUser) {
        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }


    public UserEditDTO getEditDetails(String username) {
        return modelMapper.map(userRepository.findByEmail(username).orElse(null), UserEditDTO.class);
    }

    public void update(UserEditDTO userEditDTO) {

        UserEntity newUser = userRepository.findByEmail(userEditDTO.getEmail()).orElse(null)
                .setFirstName(userEditDTO.getFirstName())
                .setLastName(userEditDTO.getLastName())
                .setImageUrl(userEditDTO.getImageUrl());
        userRepository.save(newUser);
        updateAuthentication(newUser);
    }

    public void deleteByEmail(String username) {
        userRepository.deleteByEmail(username);
        SecurityContextHolder.clearContext();
    }

    public void updatePassword(String email,String newPassword) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        updateAuthentication(userEntity);
    }

}
