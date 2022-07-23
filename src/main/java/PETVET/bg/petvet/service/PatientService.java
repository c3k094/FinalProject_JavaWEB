package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.view.OwnerTableView;
import PETVET.bg.petvet.model.view.PatientTableView;
import PETVET.bg.petvet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
}
