package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.dto.SearchPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PatientSpecification implements Specification<AnimalEntity> {

    private final SearchPatientDTO searchPatientDTO;

    public PatientSpecification(SearchPatientDTO searchPatientDTO) {
        this.searchPatientDTO = searchPatientDTO;
    }

    @Override
    public Predicate toPredicate(Root<AnimalEntity> root, CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchPatientDTO.getIdentificationNumber() != null && !searchPatientDTO.getIdentificationNumber().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("identificationNumber"), searchPatientDTO.getIdentificationNumber()))
            );
        }

        if (searchPatientDTO.getName() != null && !searchPatientDTO.getName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("name"), searchPatientDTO.getName()))
            );
        }
        return p;
    }
}
