package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManipulationService {
    private ModelMapper modelMapper;
    private PatientService patientService;

    @Autowired
    public ManipulationService(ModelMapper modelMapper, PatientService patientService) {
        this.modelMapper = modelMapper;
        this.patientService = patientService;
    }

    public ManipulationAddDTO initializeManipulation(Long id) {
        return modelMapper.map(patientService.findById(id), ManipulationAddDTO.class);
    }
}
