package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.user.AppUserDetails;
import PETVET.bg.petvet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// NOTE: This is not annotated as @Service, because we will return it as a bean.
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AppUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return userRepository.
        findByEmail(username).
        map(this::map).
        orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {
    return new AppUserDetails(
            userEntity.getPassword(),
            userEntity.getEmail(),
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.
                    getUserRoles().
                    stream().
                    map(this::map).
                    toList(),
            userEntity.isActive(),
            userEntity.isLocked());
  }

  private GrantedAuthority map(UserRoleEntity userRole) {
    return new SimpleGrantedAuthority("ROLE_" +
        userRole.
            getUserRole().name());
  }
}
