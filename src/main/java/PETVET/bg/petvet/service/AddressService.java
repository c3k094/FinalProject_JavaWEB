package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.entity.AddressEntity;
import PETVET.bg.petvet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void add(AddressEntity address) {
        addressRepository.save(address);
    }

    public Optional<AddressEntity> findByCityAndCountryAndStreet(String city, String country, String street) {
        return addressRepository.findByCityAndCountryAndStreet(city, country, street);
    }
}
