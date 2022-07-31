package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.entity.ManipulationEntity;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;
import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.model.view.ManipulationTableView;
import PETVET.bg.petvet.repository.ManipulationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManipulationService {
    private ModelMapper modelMapper;
    private PatientService patientService;
    private UserService userService;
    private ManipulationRepository manipulationRepository;

    @Autowired
    public ManipulationService(ModelMapper modelMapper, PatientService patientService, UserService userService, ManipulationRepository manipulationRepository) {
        this.modelMapper = modelMapper;
        this.patientService = patientService;
        this.userService = userService;
        this.manipulationRepository = manipulationRepository;
    }

    public ManipulationAddDTO initializeManipulation(Long id, UserDetails userDetails) {
        AnimalEntity animal = patientService.findById(id);
        ManipulationAddDTO newManipulation = new ManipulationAddDTO();
        newManipulation
                .setCastrated(animal.isCastrated())
                .setDewormed(animal.isDewormed())
                .setVaccinated(animal.isVaccinated())
                .setVaccine(animal.getVaccine())
                .setAnimalVaccinationDate(animal.getVaccinationDate())
                .setDewormingType(animal.getDewormingType())
                .setAnimalDewormingDate(animal.getDewormingDate())
                .setAnimalId(animal.getId())
                .setDoctorId(userService.findByEmail(userDetails.getUsername()).getId());

        return newManipulation;
    }

    public void addManipulation(ManipulationAddDTO manipulationAddDTO, Long id, UserDetails userDetails) {
        AnimalEntity patient = patientService.findById(id);
            if(manipulationAddDTO.getManipulation().equals(ManipulationsEnum.VACCINATION)){
                patient
                        .setVaccinated(manipulationAddDTO.isVaccinated())
                        .setVaccine(manipulationAddDTO.getVaccine())
                        .setVaccinationDate(manipulationAddDTO.getAnimalVaccinationDate());
            } else if (manipulationAddDTO.getManipulation().equals(ManipulationsEnum.DEWORMING)){
                patient
                        .setDewormed(manipulationAddDTO.isDewormed())
                        .setDewormingType(manipulationAddDTO.getDewormingType())
                        .setDewormingDate(manipulationAddDTO.getAnimalDewormingDate());
            } else if (manipulationAddDTO.getManipulation().equals(ManipulationsEnum.CASTRATION)) {
                patient
                        .setCastrated(manipulationAddDTO.isCastrated());
            }

        ManipulationEntity manipulationEntity = modelMapper.map(manipulationAddDTO, ManipulationEntity.class);
        manipulationEntity.setAnimal(patient);
        UserEntity doctor = userService.findByEmail(userDetails.getUsername());
        manipulationEntity.setDoctor(doctor);

        manipulationRepository.save(manipulationEntity);
    }

    public List<ManipulationTableView> getAllManipulationsForPatient(Long patientId) {
        return manipulationRepository.findAllByAnimalId(patientId)
                .stream()
                .map(o ->  modelMapper.map(o,ManipulationTableView.class)).collect(Collectors.toList());
    }
}
