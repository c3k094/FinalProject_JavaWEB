package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.AddOwnerDTO;
import PETVET.bg.petvet.model.entity.AddressEntity;

import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {


    private AddressService addressService;
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(AddressService addressService, OwnerRepository ownerRepository) {
        this.addressService = addressService;
        this.ownerRepository = ownerRepository;
    }

    public void addOwner(AddOwnerDTO addOwnerModel) {
        OwnerEntity newOwner = new OwnerEntity()
                .setEmail(addOwnerModel.getEmail())
                .setFirstName(addOwnerModel.getFirstName())
                .setLastName(addOwnerModel.getLastName())
                .setPhoneNumber(addOwnerModel.getPhoneNumber());

        Optional<AddressEntity> optionalAddress =
                addressService.findByCityAndCountryAndStreet(addOwnerModel.getCity(),
                        addOwnerModel.getCountry(),
                        addOwnerModel.getStreet());
        if(optionalAddress.isEmpty()){
            AddressEntity address = new AddressEntity().
                    setCity(addOwnerModel.getCity()).
                    setCountry(addOwnerModel.getCountry()).
                    setStreet(addOwnerModel.getStreet()).
                    setPostCode(addOwnerModel.getPostCode());
            newOwner.setAddress(address);
        } else {
            newOwner.setAddress(optionalAddress.get());
        }

        ownerRepository.save(newOwner);
    }

    public Optional<OwnerEntity> findByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    public Optional<OwnerEntity> findByPhoneNumber(String phoneNumber) {
        return ownerRepository.findByPhoneNumber(phoneNumber);
    }
}
