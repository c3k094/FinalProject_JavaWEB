package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.UserRoleEntity;
import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByUserRole(UserRoleEnum role);
}
