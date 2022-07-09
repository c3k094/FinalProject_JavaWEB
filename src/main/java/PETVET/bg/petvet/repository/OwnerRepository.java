package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

    Optional<OwnerEntity> findByEmail(String email);

    Optional<OwnerEntity> findByPhoneNumber(String phoneNumber);
}
