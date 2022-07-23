package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.view.OwnerDetailsView;
import PETVET.bg.petvet.model.view.PatientDetailsView;
import PETVET.bg.petvet.model.view.PatientTableView;
import PETVET.bg.petvet.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<AnimalEntity> findByIdentificationNumber(String identificationNumber) {
        return patientRepository.findByIdentificationNumber(identificationNumber);
    }

    public void save(AnimalEntity newPatient) {
        patientRepository.save(newPatient);
    }

    public List<PatientTableView> findViewAll() {
        return this.patientRepository.findAll().stream()
                .map(o -> new PatientTableView()
                        .setId(o.getId())
                        .setName(o.getName())
                        .setAnimalType(o.getAnimalType())
                        .setBirthday(o.getBirthday())
                        .setIdentificationNumber(o.getIdentificationNumber())
                        .setOwner(o.getOwner())).collect(Collectors.toList());
    }

    public PatientDetailsView findPatientDetailsById(Long id) {
        return modelMapper.map(patientRepository.findById(id), PatientDetailsView.class);
    }

    public void deleteById(Long id) {
        this.patientRepository.deleteById(id);
    }

    public EditPatientDTO getEditPatientDTOById(Long id) {
        return modelMapper.map(patientRepository.findById(id),EditPatientDTO.class);
    }
}
