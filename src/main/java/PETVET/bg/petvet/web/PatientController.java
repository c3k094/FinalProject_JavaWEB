package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AddPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.service.OwnerService;
import PETVET.bg.petvet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    private OwnerService ownerService;
    private PatientService patientService;

    @Autowired
    public PatientController(OwnerService ownerService, PatientService patientService) {
        this.ownerService = ownerService;
        this.patientService = patientService;
    }

    @ModelAttribute("addPatientModel")
    public void initPatientModel(Model model) {
        model.addAttribute("addPatientModel", new AddPatientDTO());
    }

    @GetMapping("/patients/add")
    public String addPatient(Model model, @RequestParam Optional<Long> ownerId){
            List<OwnerDropDownView> owners = ownerService.findAll();
            model.addAttribute("owners", ownerId.isEmpty() ? owners : new ArrayList<OwnerDropDownView>());
            model.addAttribute("ownerId", ownerId.get());
    return "patient-add";
    }
    @PostMapping("/patients/add")
    public String addPatient(@Valid AddPatientDTO addPatientModel,
                             @RequestParam Optional<Long> ownerId,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ownerId.ifPresent(addPatientModel::setOwnerId);
        List<OwnerDropDownView> owners = ownerService.findAll();
        Optional<AnimalEntity> optionalPatient = patientService.findByIdentificationNumber(addPatientModel.getIdentificationNumber());
        if (bindingResult.hasErrors() || optionalPatient.isPresent()){
            redirectAttributes.addFlashAttribute("addPatientModel", addPatientModel);
            redirectAttributes.addFlashAttribute("owners", ownerId.isEmpty() ? owners : new ArrayList<OwnerDropDownView>());
            redirectAttributes.addFlashAttribute("patientExist", optionalPatient.isPresent());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPatientModel",
                    bindingResult);
            return "redirect:/patients/add";
        }
        AnimalEntity newPatient = new AnimalEntity()
                .setOwner(ownerService.findById(addPatientModel.getOwnerId()))
                .setName(addPatientModel.getName())
                .setAnimalType(addPatientModel.getAnimalType())
                .setVaccinated(addPatientModel.isVaccinated())
                .setBirthday(addPatientModel.getBirthdate())
                .setIdentificationNumber(addPatientModel.getIdentificationNumber())
                .setBreed(addPatientModel.getBreed());

        patientService.save(newPatient);
        return "redirect:/patients/all";
    }
}
