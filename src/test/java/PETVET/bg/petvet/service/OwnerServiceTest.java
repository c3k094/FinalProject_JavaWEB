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
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository mockOwnerRepo;

    private OwnerService toTest;
    @Mock
    private AddressService addressService;

    private OwnerEntity ownerEntity;

    private AddressEntity address;

    @BeforeEach
    void setUp() {
        toTest = new OwnerService(
                addressService,
                mockOwnerRepo,
                new ModelMapper()
        );
        address = new AddressEntity()
                .setCity("Varna")
                .setCountry("Bulgaria")
                .setStreet("Ivan Maslev 7")
                .setPostcode("51423");
        ownerEntity = new OwnerEntity()
                .setAddress(address)
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743");
    }

    @Test
    void testAddOwner_WithExistingAddess() {
        AddOwnerDTO addOwnerDTO = new AddOwnerDTO()
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743")
                .setCity("Varna")
                .setCountry("Bulgaria")
                .setStreet("Ivan Maslev 7")
                .setPostcode("51423");


        when(mockOwnerRepo.save(any())).thenReturn(ownerEntity);
        when(addressService.findByCityAndCountryAndStreet(address.getCity(), address.getCountry(), address.getStreet())).thenReturn(Optional.of(address));

        toTest.addOwner(addOwnerDTO);

        verify(mockOwnerRepo,times(1)).save(any());
    }

    @Test
    void testAddOwner_WithNonExistingAddress() {
        AddOwnerDTO addOwnerDTO = new AddOwnerDTO()
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743")
                .setCity("Varna")
                .setCountry("Bulgaria")
                .setStreet("Ivan Maslev 7")
                .setPostcode("51423");


        when(mockOwnerRepo.save(any())).thenReturn(ownerEntity);
        when(addressService.findByCityAndCountryAndStreet(address.getCity(), address.getCountry(), address.getStreet())).thenReturn(Optional.empty());

        toTest.addOwner(addOwnerDTO);

        verify(mockOwnerRepo,times(1)).save(any());
    }

    @Test
    void testFindByEmail_FoundSuccessfully(){
        String email = ownerEntity.getEmail();

        when(mockOwnerRepo.findByEmail(email)).thenReturn(Optional.of(ownerEntity));

        Optional<OwnerEntity> returnedOwner = toTest.findByEmail(email);

        Assertions.assertEquals(ownerEntity.getEmail(),returnedOwner.get().getEmail());
        Assertions.assertEquals(ownerEntity.getFirstName(),returnedOwner.get().getFirstName());
        Assertions.assertEquals(ownerEntity.getLastName(),returnedOwner.get().getLastName());
        Assertions.assertEquals(ownerEntity.getPhoneNumber(),returnedOwner.get().getPhoneNumber());
    }

    @Test
    void testFindByPhoneNumber_FoundSuccessfully(){
        String phone = ownerEntity.getPhoneNumber();

        when(mockOwnerRepo.findByPhoneNumber(phone)).thenReturn(Optional.of(ownerEntity));

        Optional<OwnerEntity> returnedOwner = toTest.findByPhoneNumber(phone);

        Assertions.assertEquals(ownerEntity.getEmail(),returnedOwner.get().getEmail());
        Assertions.assertEquals(ownerEntity.getFirstName(),returnedOwner.get().getFirstName());
        Assertions.assertEquals(ownerEntity.getLastName(),returnedOwner.get().getLastName());
        Assertions.assertEquals(ownerEntity.getPhoneNumber(),returnedOwner.get().getPhoneNumber());
    }

    @Test
    void testFindAll_Matches() {
        List<OwnerDropDownView> expectedOwnersView = new ArrayList<>();
        OwnerDropDownView ownerDropDownView1 = new OwnerDropDownView()
                .setId(ownerEntity.getId())
                .setName(ownerEntity.getFirstName() + " " + ownerEntity.getLastName());
        expectedOwnersView.add(ownerDropDownView1);

        List<OwnerEntity> expectedOwnersEntity = new ArrayList<>();
        expectedOwnersEntity.add(ownerEntity);

        when(mockOwnerRepo.findAll()).thenReturn(expectedOwnersEntity);

        List<OwnerDropDownView> returnedOwnersView = toTest.findAll();

        Assertions.assertEquals(expectedOwnersView.size(), returnedOwnersView.size());
    }

    @Test
    void testFindById_FoundSuccessfully(){
        Long ownerId = ownerEntity.getId();

        when(mockOwnerRepo.findById(ownerId)).thenReturn(Optional.of(ownerEntity));

        OwnerEntity returnedOwner = toTest.findById(ownerId);

        Assertions.assertEquals(ownerEntity.getEmail(),returnedOwner.getEmail());
        Assertions.assertEquals(ownerEntity.getFirstName(),returnedOwner.getFirstName());
        Assertions.assertEquals(ownerEntity.getLastName(),returnedOwner.getLastName());
        Assertions.assertEquals(ownerEntity.getPhoneNumber(),returnedOwner.getPhoneNumber());
    }
    @Test
    void testFindViewAll_FoundSuccessfully() {
        List<OwnerTableView> expectedOwnersView = new ArrayList<>();
        OwnerTableView ownerView1 = new OwnerTableView();
        ownerView1
                .setEmail(ownerEntity.getEmail())
                .setPhoneNumber(ownerEntity.getPhoneNumber())
                .setFirstName(ownerEntity.getFirstName())
                .setLastName(ownerEntity.getLastName())
                .setId(ownerEntity.getId());
        expectedOwnersView.add(ownerView1);

        List<OwnerEntity> expectedOwnersEntity = new ArrayList<>();
        expectedOwnersEntity.add(ownerEntity);

        Page<OwnerEntity> page = new PageImpl<>(expectedOwnersEntity);

        when(mockOwnerRepo.findAll(Pageable.ofSize(10))).thenReturn(page);

        Page<OwnerTableView> returnedOwnersView = toTest.findViewAll(Pageable.ofSize(10));

        Assertions.assertEquals(expectedOwnersView.size(), returnedOwnersView.getTotalElements());
    }

    @Test
    void testFindOwnerDetailsById_FoundSuccessfully(){
        Long ownerId = ownerEntity.getId();

        when(mockOwnerRepo.findById(ownerId)).thenReturn(Optional.of(ownerEntity));

        OwnerDetailsView returnedOwner = toTest.findOwnerDetailsById(ownerId);

        Assertions.assertEquals(ownerEntity.getEmail(),returnedOwner.getEmail());
        Assertions.assertEquals(ownerEntity.getFirstName(),returnedOwner.getFirstName());
        Assertions.assertEquals(ownerEntity.getLastName(),returnedOwner.getLastName());
        Assertions.assertEquals(ownerEntity.getPhoneNumber(),returnedOwner.getPhoneNumber());
    }

    @Test
    void testFindOwnerDetailsById_NotFound(){
        Long ownerId = ownerEntity.getId();

        when(mockOwnerRepo.findById(ownerId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> toTest.findOwnerDetailsById(ownerId));
    }

    @Test
    void testDeleteById(){
        Long ownerId = ownerEntity.getId();

        toTest.deleteById(ownerId);
        verify(mockOwnerRepo,times(1)).deleteById(ownerId);
    }

    @Test
    void testGetEditOwnerDTOById_FoundSuccessfully(){
        Long ownerId = ownerEntity.getId();

        when(mockOwnerRepo.findById(ownerId)).thenReturn(Optional.of(ownerEntity));

        EditOwnerDTO returnedOwner = toTest.getEditOwnerDTOById(ownerId);

        Assertions.assertEquals(ownerEntity.getEmail(),returnedOwner.getEmail());
        Assertions.assertEquals(ownerEntity.getFirstName(),returnedOwner.getFirstName());
        Assertions.assertEquals(ownerEntity.getLastName(),returnedOwner.getLastName());
        Assertions.assertEquals(ownerEntity.getPhoneNumber(),returnedOwner.getPhoneNumber());
    }
    @Test
    void testGetEditOwnerDTOById_NotFound(){
        Long ownerId = ownerEntity.getId();

        when(mockOwnerRepo.findById(ownerId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> toTest.getEditOwnerDTOById(ownerId));
    }

    @Test
    void testUpdateOwnerWithExistingAddress_Successfully() {
        EditOwnerDTO editOwnerDTO = new EditOwnerDTO()
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743")
                .setAddressCity("Varna")
                .setAddressCountry("Bulgaria")
                .setAddressStreet("Ivan Maslev 7")
                .setAddressPostcode("51423")
                .setId(ownerEntity.getId());

            when(mockOwnerRepo.findById(editOwnerDTO.getId())).thenReturn(Optional.of(ownerEntity));
          when(addressService.findByCityAndCountryAndStreet(editOwnerDTO.getAddressCity(), editOwnerDTO.getAddressCountry(), editOwnerDTO.getAddressStreet())).thenReturn(Optional.of(address));

            toTest.updateOwner(editOwnerDTO);

            verify(mockOwnerRepo,times(1)).save(any());

    }

    @Test
    void testUpdateOwnerWithNonExistingAddress_Successfully() {
        EditOwnerDTO editOwnerDTO = new EditOwnerDTO()
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743")
                .setAddressCity("asfasg")
                .setAddressCountry("Bulgaria")
                .setAddressStreet("Ivan Maslev 7")
                .setAddressPostcode("51423")
                .setId(ownerEntity.getId());

        when(mockOwnerRepo.findById(editOwnerDTO.getId())).thenReturn(Optional.of(ownerEntity));
        when(addressService.findByCityAndCountryAndStreet(editOwnerDTO.getAddressCity(), editOwnerDTO.getAddressCountry(), editOwnerDTO.getAddressStreet())).thenReturn(Optional.empty());

        toTest.updateOwner(editOwnerDTO);

        verify(mockOwnerRepo,times(1)).save(any());

    }
    @Test
    void testSearchOwner() {
        SearchOwnerDTO searchOwnerDTO = new SearchOwnerDTO()
                .setEmail(ownerEntity.getEmail())
                .setPhoneNumber(ownerEntity.getPhoneNumber());

        List<OwnerEntity> expectedOwnersEntity = new ArrayList<>();
        expectedOwnersEntity.add(ownerEntity);

        Page<OwnerEntity> page = new PageImpl<>(expectedOwnersEntity);

        when(mockOwnerRepo.findAll((Specification<OwnerEntity>) any(), (Pageable) any())).thenReturn(page);

        Page<OwnerTableView> returnedOwnersView = toTest.searchOwner(searchOwnerDTO,Pageable.ofSize(10));

        Assertions.assertEquals(expectedOwnersEntity.size(), returnedOwnersView.getTotalElements());
    }
}
