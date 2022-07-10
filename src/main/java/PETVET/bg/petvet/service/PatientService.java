package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
