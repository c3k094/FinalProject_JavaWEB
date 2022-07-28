package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.ManipulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManipulationRepository extends JpaRepository<ManipulationEntity, Long> {

}
