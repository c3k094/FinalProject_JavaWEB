package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.UserEditDTO;
import PETVET.bg.petvet.model.dto.UserRegisterDTO;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;
import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.model.mapper.UserMapper;
import PETVET.bg.petvet.repository.UserRepository;
import PETVET.bg.petvet.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ApplicationListener<AuthenticationSuccessEvent> {

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

    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found!"));
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
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                        .setLastLoginDate(LocalDateTime.now());


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
        return modelMapper.map(userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("There is no such user!")), UserEditDTO.class);
    }

    public void update(UserEditDTO userEditDTO) {

        UserEntity newUser = userRepository.findByEmail(userEditDTO.getEmail()).orElseThrow(() -> new NotFoundException("There is no such user!"))
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
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("There is no such user!"));
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        updateAuthentication(userEntity);
        userRepository.save(userEntity);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userEmail = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        userRepository.save(findByEmail(userEmail).setLastLoginDate(LocalDateTime.now()));

    }
    public boolean isBlocked(String userName) {
        UserEntity user = findByEmail(userName);
        return !user.isActive() || user.isLocked();
    }

    @Scheduled(cron = "0 0 12 * * ?") //10 * * * * ?
    public void scheduleDeactivateUserAccount() {
        LocalDateTime currTime = LocalDateTime.now();
        userRepository.findAll()
                .forEach(u -> {
                    Duration duration = Duration.between(u.getLastLoginDate(), currTime);
                    if(duration.toDays() > 90 && u.isActive()) {
                        u.setActive(false);
                        userRepository.save(u);
                    }
                });

//        long now = System.currentTimeMillis() / 1000;
//        System.out.println(
//                "schedule tasks using cron jobs - " + now);
   }
}
