package PETVET.bg.petvet.repository;

import PETVET.bg.petvet.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByCityAndCountryAndStreet(String city, String country, String street);
}
