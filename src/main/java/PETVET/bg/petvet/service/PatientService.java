package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.dto.SearchOwnerDTO;
import PETVET.bg.petvet.model.dto.SearchPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.model.view.OwnerTableView;
import PETVET.bg.petvet.model.view.PatientDetailsView;
import PETVET.bg.petvet.model.view.PatientTableView;
import PETVET.bg.petvet.repository.OwnerSpecification;
import PETVET.bg.petvet.repository.PatientRepository;
import PETVET.bg.petvet.repository.PatientSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    private OwnerService ownerService;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper, OwnerService ownerService) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.ownerService = ownerService;
    }

    public Optional<AnimalEntity> findByIdentificationNumber(String identificationNumber) {
        return patientRepository.findByIdentificationNumber(identificationNumber);
    }

    public void save(AnimalEntity newPatient) {
        patientRepository.save(newPatient);
    }

    public Page<PatientTableView> findViewAll(Pageable pageable) {
        return this.patientRepository.findAll(pageable)
                .map(o -> new PatientTableView()
                        .setId(o.getId())
                        .setName(o.getName())
                        .setAnimalType(o.getAnimalType())
                        .setBirthday(o.getBirthday())
                        .setIdentificationNumber(o.getIdentificationNumber())
                        .setOwner(o.getOwner()));
    }

    public PatientDetailsView findPatientDetailsById(Long id) {
        return modelMapper.map(patientRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such patient found!")), PatientDetailsView.class);
    }

    public void deleteById(Long id) {
        this.patientRepository.deleteById(id);
    }

    public EditPatientDTO getEditPatientDTOById(Long id) {
        return modelMapper.map(patientRepository.findById(id),EditPatientDTO.class);
    }

    public String findIdentificationNumberById(Long id) {
        return this.patientRepository.findById(id).orElseThrow(() -> new NotFoundException("We cannot find the identification number for this patient, as such patient actually exist!")).getIdentificationNumber();
    }

    public void updatePatient(EditPatientDTO editPatientDTO) {
        AnimalEntity updatedAnimal =  this.patientRepository.findById(editPatientDTO.getId()).orElseThrow(() -> new NotFoundException("There is no such patient!"));
        updatedAnimal
                .setName(editPatientDTO.getName())
                .setAnimalType(editPatientDTO.getAnimalType())
                .setVaccinated(editPatientDTO.isVaccinated())
                .setVaccine(editPatientDTO.getVaccine())
                .setVaccinationDate(editPatientDTO.getVaccinationDate())
                .setOwner(ownerService.findById(editPatientDTO.getOwnerId()))
                .setBirthday(editPatientDTO.getBirthday())
                .setBreed(editPatientDTO.getBreed())
                .setDewormed(editPatientDTO.isDewormed())
                .setDewormingType(editPatientDTO.getDewormingType())
                .setDewormingDate(editPatientDTO.getDewormingDate())
                .setCastrated(editPatientDTO.isCastrated());

        patientRepository.save(updatedAnimal);
    }

    public AnimalEntity findById(Long id) {
        return this.patientRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such patient!"));
    }

    public Page<PatientTableView> searchPatient(SearchPatientDTO searchPatientDTO, Pageable pageable) {

        return this.patientRepository.findAll(new PatientSpecification(searchPatientDTO), pageable)
                .map(patient -> modelMapper.map(patient, PatientTableView.class));
    }
}
