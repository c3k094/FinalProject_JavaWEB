package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<AnimalEntity, Long>, JpaSpecificationExecutor<AnimalEntity> {

    Optional<AnimalEntity> findByIdentificationNumber(String identificationNumber);

}
