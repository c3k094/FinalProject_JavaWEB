package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.dto.SearchOwnerDTO;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OwnerSpecification implements Specification<OwnerEntity> {

    private final SearchOwnerDTO searchOwnerDTO;

    public OwnerSpecification(SearchOwnerDTO searchOwnerDTO) {
        this.searchOwnerDTO = searchOwnerDTO;
    }

    @Override
    public Predicate toPredicate(Root<OwnerEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchOwnerDTO.getEmail() != null && !searchOwnerDTO.getEmail().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("email"), searchOwnerDTO.getEmail()))
            );
        }

        if (searchOwnerDTO.getPhoneNumber() != null && !searchOwnerDTO.getPhoneNumber().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("phoneNumber"), searchOwnerDTO.getPhoneNumber()))
            );
        }
        return p;
    }
}
