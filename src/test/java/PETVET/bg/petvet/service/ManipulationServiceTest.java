package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.model.entity.*;
import PETVET.bg.petvet.model.entity.enums.DewormingType;
import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;
import PETVET.bg.petvet.model.user.AppUserDetails;
import PETVET.bg.petvet.model.view.ManipulationTableView;
import PETVET.bg.petvet.repository.ManipulationRepository;
import PETVET.bg.petvet.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManipulationServiceTest {
    @Mock
    private ManipulationRepository mockManipulationRepo;
    @Mock
    private PatientService patientService;
    @Mock
    UserService userService;

    private AnimalEntity patient;

    private ManipulationService toTest;

    private Date mockBirthdate;

    private OwnerEntity owner;

    private AddressEntity address;

    private Long patientId;

    private UserEntity user;

    private LocalDateTime loginDate;

    private UserDetails userDetails;

    private Date manipDate;

    @BeforeEach
    void setUp() {
        toTest = new ManipulationService(
                new ModelMapper(),
                patientService,
                userService,
                mockManipulationRepo
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

        loginDate = LocalDateTime.now();

        patient = (AnimalEntity) new AnimalEntity()
                .setName("John")
                .setAnimalType("Dog")
                .setBirthday(mockBirthdate)
                .setOwner(owner)
                .setIdentificationNumber("123456789")
                .setBreed("Huskey")
                .setCastrated(true)
                .setVaccinated(true)
                .setVaccine("Pfizer")
                .setVaccinationDate(mockBirthdate)
                .setId(1L);
                 patientId = patient.getId();

                 user = (UserEntity) new UserEntity()
                         .setFirstName("Preslav")
                         .setLastName("Hristov")
                         .setEmail("p.hristov@mail.bg")
                         .setImageUrl("image.com")
                         .setLastLoginDate(loginDate)
                         .setActive(true)
                         .setLocked(false)
                         .setId(1L);
                userDetails = new AppUserDetails(
                        "topsecret",
                        "p.hristov@mail.bg",
                        "Preslav",
                        "Hristov",
                        new ArrayList<>(),
                        true,
                        false);
    }

    @Test
    void testInitializeManipulation() {
        when(patientService.findById(patientId)).thenReturn(patient);
        when(userService.findByEmail(user.getEmail())).thenReturn(user);

        ManipulationAddDTO manipulationAddDTO = toTest.initializeManipulation(patientId,userDetails);

        Assertions.assertEquals(patientId, manipulationAddDTO.getAnimalId());
        Assertions.assertEquals(user.getId(), manipulationAddDTO.getDoctorId());
        Assertions.assertEquals(patient.isCastrated(), manipulationAddDTO.isCastrated());
        Assertions.assertEquals(patient.isVaccinated(), manipulationAddDTO.isVaccinated());
        Assertions.assertEquals(patient.isVaccinated(), manipulationAddDTO.isVaccinated());
        Assertions.assertEquals(patient.getVaccine(), manipulationAddDTO.getVaccine());
        Assertions.assertEquals(patient.getVaccinationDate(), manipulationAddDTO.getAnimalVaccinationDate());
        Assertions.assertEquals(patient.isDewormed(), manipulationAddDTO.isDewormed());
    }

    @Test
    void testAddManipulation_Dewroming() {
        when(patientService.findById(patientId)).thenReturn(patient);
        when(userService.findByEmail(user.getEmail())).thenReturn(user);

        ManipulationAddDTO manipulationAddDTO = new ManipulationAddDTO()
                .setManipulation(ManipulationsEnum.DEWORMING)
                .setManipulationDate(manipDate)
                .setDewormed(true)
                .setDewormingType(DewormingType.BOTH)
                .setAnimalDewormingDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimalId(patientId)
                .setDoctorId(user.getId());

        manipDate = new Date(2022,8,8);

        ManipulationEntity manipulation = new ManipulationEntity()
                .setManipulation(ManipulationsEnum.DEWORMING)
                .setManipulationDate(manipDate)
                .setDewormed(true)
                .setDewormingType(DewormingType.BOTH)
                .setAnimalDewormingDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimal(patient)
                .setDoctor(user);

        toTest.addManipulation(manipulationAddDTO, patientId, userDetails);

        verify(mockManipulationRepo,times(1)).save(any());

    }
    @Test
    void testAddManipulation_Vaccination() {
        when(patientService.findById(patientId)).thenReturn(patient);
        when(userService.findByEmail(user.getEmail())).thenReturn(user);

        ManipulationAddDTO manipulationAddDTO = new ManipulationAddDTO()
                .setManipulation(ManipulationsEnum.VACCINATION)
                .setManipulationDate(manipDate)
                .setVaccinated(true)
                .setVaccine("Pfizer")
                .setAnimalVaccinationDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimalId(patientId)
                .setDoctorId(user.getId());

        manipDate = new Date(2022,8,8);

        ManipulationEntity manipulation = new ManipulationEntity()
                .setManipulation(ManipulationsEnum.VACCINATION)
                .setVaccinated(true)
                .setVaccine("Pfizer")
                .setAnimalVaccinationDate(manipDate)
                .setAnimalDewormingDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimal(patient)
                .setDoctor(user);

        toTest.addManipulation(manipulationAddDTO, patientId, userDetails);

        verify(mockManipulationRepo,times(1)).save(any());

    }

    @Test
    void testAddManipulation_Castration() {
        when(patientService.findById(patientId)).thenReturn(patient);
        when(userService.findByEmail(user.getEmail())).thenReturn(user);

        ManipulationAddDTO manipulationAddDTO = new ManipulationAddDTO()
                .setManipulation(ManipulationsEnum.CASTRATION)
                .setManipulationDate(manipDate)
                .setCastrated(true)
                .setAdditionalInformation("TestTestTest!")
                .setAnimalId(patientId)
                .setDoctorId(user.getId());

        manipDate = new Date(2022,8,8);

        ManipulationEntity manipulation = new ManipulationEntity()
                .setManipulation(ManipulationsEnum.CASTRATION)
                .setCastrated(true)
                .setAnimalDewormingDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimal(patient)
                .setDoctor(user);

        toTest.addManipulation(manipulationAddDTO, patientId, userDetails);

        verify(mockManipulationRepo,times(1)).save(any());
    }

    @Test
    void testGetAllManipulationsForPatient() {
        ManipulationEntity manipulation = new ManipulationEntity()
                .setManipulation(ManipulationsEnum.CASTRATION)
                .setCastrated(true)
                .setAnimalDewormingDate(manipDate)
                .setAdditionalInformation("TestTestTest!")
                .setAnimal(patient)
                .setDoctor(user);

        List<ManipulationEntity> expectedManipList = new ArrayList<>();
        expectedManipList.add(manipulation);

        when(mockManipulationRepo.findAllByAnimalId(patientId)).thenReturn(expectedManipList);

        List<ManipulationTableView> manipulationTableViewList = toTest.getAllManipulationsForPatient(patientId);

        Assertions.assertEquals(expectedManipList.size(), manipulationTableViewList.size());


    }
}
