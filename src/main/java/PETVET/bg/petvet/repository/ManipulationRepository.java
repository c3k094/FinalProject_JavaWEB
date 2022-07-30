package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.ManipulationEntity;
import PETVET.bg.petvet.model.view.ManipulationTableView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManipulationRepository extends JpaRepository<ManipulationEntity, Long> {

    List<ManipulationEntity> findAllByAnimalId(Long patientId);
}
