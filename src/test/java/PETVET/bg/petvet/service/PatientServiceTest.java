package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.entity.AddressEntity;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.model.view.OwnerDetailsView;
import PETVET.bg.petvet.model.view.OwnerTableView;
import PETVET.bg.petvet.model.view.PatientDetailsView;
import PETVET.bg.petvet.model.view.PatientTableView;
import PETVET.bg.petvet.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private PatientRepository mockPatientRepo;
    @Mock
    private OwnerService ownerService;

    private PatientService toTest;

    private OwnerEntity owner;

    private AddressEntity address;

    private AnimalEntity patient;

    private Date mockBirthdate;

    private Long patientId;

    @BeforeEach
    void setUp() {
        toTest = new PatientService(
                mockPatientRepo,
                new ModelMapper(),
                ownerService
        );
        address = new AddressEntity()
                .setCity("Varna")
                .setCountry("Bulgaria")
                .setStreet("Ivan Maslev 7")
                .setPostcode("51423");

        owner = new OwnerEntity()
                .setAddress(address)
                .setEmail("test@example.com")
                .setFirstName("Boris")
                .setLastName("Jonson")
                .setPhoneNumber("0886589743");

        mockBirthdate = new Date(2021, 11,04);
        patient = (AnimalEntity) new AnimalEntity()
                .setName("John")
                .setAnimalType("Dog")
                .setBirthday(mockBirthdate)
                .setOwner(owner)
                .setIdentificationNumber("123456789")
                .setBreed("Huskey")
                .setId(1L);
        patientId = patient.getId();
    }

    @Test
    void testFindByIdentificationNumber_FoundSuccessfully(){
        String identNumber = patient.getIdentificationNumber();

        when(mockPatientRepo.findByIdentificationNumber(identNumber)).thenReturn(Optional.of(patient));

        Optional<AnimalEntity> returnedPatient = toTest.findByIdentificationNumber(identNumber);

        Assertions.assertEquals(patient.getName(), returnedPatient.get().getName());
        Assertions.assertEquals(patient.getAnimalType(), returnedPatient.get().getAnimalType());
        Assertions.assertEquals(patient.getIdentificationNumber(), returnedPatient.get().getIdentificationNumber());
        Assertions.assertEquals(patient.getBirthday(), returnedPatient.get().getBirthday());

    }

    @Test
    void testSave_Successful(){
        toTest.save(patient);
        verify(mockPatientRepo,times(1)).save(any());
    }

    @Test
    void testFindViewAll() {
        List<AnimalEntity> expectedPatientEntity = new ArrayList<>();
        expectedPatientEntity.add(patient);
        Page<AnimalEntity> page = new PageImpl<>(expectedPatientEntity);

        when(mockPatientRepo.findAll(Pageable.ofSize(10))).thenReturn(page);

        Page<PatientTableView> returnedPatientsView = toTest.findViewAll(Pageable.ofSize(10));

        Assertions.assertEquals(expectedPatientEntity.size(), returnedPatientsView.getTotalElements());
    }

    @Test
    void testFindPatientDetailsById_SuccessfullyFound() {
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.of(patient));

        PatientDetailsView returnedPatient = toTest.findPatientDetailsById(patientId);

        Assertions.assertEquals(patient.getName(), returnedPatient.getName());
        Assertions.assertEquals(patient.getAnimalType(), returnedPatient.getAnimalType());
        Assertions.assertEquals(patient.getIdentificationNumber(), returnedPatient.getIdentificationNumber());
        Assertions.assertEquals(patient.getBirthday(), returnedPatient.getBirthday());
    }

    @Test
    void testFindPatientDetailsById_NotFound(){
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> toTest.findPatientDetailsById(patientId));
    }
    @Test
    void testDeleteById(){
        toTest.deleteById(patientId);
        verify(mockPatientRepo,times(1)).deleteById(patientId);
    }
    @Test
    void testGetEditPatientDTOById() {
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.of(patient));

        EditPatientDTO returnedPatient = toTest.getEditPatientDTOById(patientId);

        Assertions.assertEquals(patient.getName(), returnedPatient.getName());
        Assertions.assertEquals(patient.getId(), returnedPatient.getId());
        Assertions.assertEquals(patient.getAnimalType(), returnedPatient.getAnimalType());
        Assertions.assertEquals(patient.getBirthday(), returnedPatient.getBirthday());
    }
    @Test
    void testFindIdentificationNumberById_SuccessfullyFound() {
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.of(patient));
        String returnedId = toTest.findIdentificationNumberById(patientId);

        Assertions.assertEquals(patient.getIdentificationNumber(), returnedId);
    }

    @Test
    void testFindIdentificationNumberById_NotFound(){
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> toTest.findIdentificationNumberById(patientId));
    }
    @Test
    void testUpdatePatient_Successful() {
        EditPatientDTO editPatientDTO = new EditPatientDTO()
                .setName("John")
                .setAnimalType("Dog")
                .setBirthday(mockBirthdate)
                .setBreed("Huskey")
                .setCastrated(true)
                .setVaccinated(true)
                .setVaccine("Pfizer")
                .setVaccinationDate(mockBirthdate)
                .setOwnerId(1L)
                .setId(1L);

        when(mockPatientRepo.findById(editPatientDTO.getId())).thenReturn(Optional.of(patient));
        when(ownerService.findById(editPatientDTO.getOwnerId())).thenReturn(owner);

        toTest.updatePatient(editPatientDTO);

        verify(mockPatientRepo,times(1)).save(patient);
    }
    @Test
    void testFindById_FoundSuccessfully(){
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.of(patient));

        AnimalEntity returnedPatient = toTest.findById(patientId);

        Assertions.assertEquals(patient.getName(), returnedPatient.getName());
        Assertions.assertEquals(patient.getAnimalType(), returnedPatient.getAnimalType());
        Assertions.assertEquals(patient.getIdentificationNumber(), returnedPatient.getIdentificationNumber());
        Assertions.assertEquals(patient.getBirthday(), returnedPatient.getBirthday());
    }

    @Test
    void testFindById_NotFound(){
        when(mockPatientRepo.findById(patientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> toTest.findById(patientId));
    }
}
