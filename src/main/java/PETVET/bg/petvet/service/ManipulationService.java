package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.entity.ManipulationEntity;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;
import PETVET.bg.petvet.repository.ManipulationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public ManipulationAddDTO initializeManipulation(Long id) {
        return modelMapper.map(patientService.findById(id), ManipulationAddDTO.class);
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
        UserEntity doctor = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        manipulationEntity.setDoctor(doctor);

        manipulationRepository.save(manipulationEntity);
    }
}
