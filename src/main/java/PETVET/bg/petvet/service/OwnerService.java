package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.AddOwnerDTO;
import PETVET.bg.petvet.model.dto.EditOwnerDTO;
import PETVET.bg.petvet.model.dto.SearchOwnerDTO;
import PETVET.bg.petvet.model.entity.AddressEntity;

import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.model.view.OwnerDetailsView;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.model.view.OwnerTableView;
import PETVET.bg.petvet.repository.OwnerRepository;
import PETVET.bg.petvet.repository.OwnerSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {


    private AddressService addressService;
    private OwnerRepository ownerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OwnerService(AddressService addressService, OwnerRepository ownerRepository, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
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
                    setPostcode(addOwnerModel.getPostcode());
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

    public List<OwnerDropDownView> findAll() {
        return ownerRepository.findAll().stream()
                .map(o -> new OwnerDropDownView().setId(o.getId()).setName(o.getFirstName()
                        + " "
                        + o.getLastName()
                        + " - "
                        + o.getPhoneNumber())).collect(Collectors.toList());
    }

    public OwnerEntity findById(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new NotFoundException("There is no such owner!"));
    }

    public Page<OwnerTableView> findViewAll(Pageable pageable) {
        return ownerRepository.findAll(pageable)
                .map(o -> new OwnerTableView()
                        .setId(o.getId())
                        .setFirstName(o.getFirstName())
                        .setLastName(o.getLastName())
                        .setPhoneNumber(o.getPhoneNumber())
                        .setEmail(o.getEmail()));
    }

    public OwnerDetailsView findOwnerDetailsById(Long id) {
        return modelMapper.map(ownerRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such owner!")), OwnerDetailsView.class);
    }

    public void deleteById(Long id) {
        this.ownerRepository.deleteById(id);
    }

    public EditOwnerDTO getEditOwnerDTOById(Long id) {
        return modelMapper.map(ownerRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such owner!")), EditOwnerDTO.class);
    }

    public void updateOwner(EditOwnerDTO editOwnerDTO) {
        OwnerEntity updatedOwner =  this.ownerRepository.findById(editOwnerDTO.getId()).orElseThrow(() -> new NotFoundException("There is no such owner! You cannot save changes!"));
        updatedOwner
                .setFirstName(editOwnerDTO.getFirstName())
                .setLastName(editOwnerDTO.getLastName())
                .setEmail(editOwnerDTO.getEmail())
                .setPhoneNumber(editOwnerDTO.getPhoneNumber());

        Optional<AddressEntity> optionalAddress =
                addressService.findByCityAndCountryAndStreet(editOwnerDTO.getAddressCity(),
                        editOwnerDTO.getAddressCountry(),
                        editOwnerDTO.getAddressStreet());
        if(optionalAddress.isEmpty()){
            AddressEntity address = new AddressEntity().
                    setCity(editOwnerDTO.getAddressCity()).
                    setCountry(editOwnerDTO.getAddressCountry()).
                    setStreet(editOwnerDTO.getAddressStreet()).
                    setPostcode(editOwnerDTO.getAddressPostcode());
            updatedOwner.setAddress(address);
        } else {
            updatedOwner.setAddress(optionalAddress.get());
        }

        ownerRepository.save(updatedOwner);
    }

    public Page<OwnerTableView> searchOwner(SearchOwnerDTO searchOwnerDTO, Pageable pageable) {

        return this.ownerRepository.findAll(new OwnerSpecification(searchOwnerDTO),pageable)
                .map(owner -> modelMapper.map(owner,OwnerTableView.class));
    }
}
