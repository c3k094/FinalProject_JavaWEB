package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.OwnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long>, JpaSpecificationExecutor<OwnerEntity> {

    Optional<OwnerEntity> findByEmail(String email);

    Optional<OwnerEntity> findByPhoneNumber(String phoneNumber);

}
