package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AddPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.model.view.PatientTableView;
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
        model.addAttribute("addPatientDTO", new AddPatientDTO());
    }

    @GetMapping("/patients/all")
    public String allPatients(Model model){
        List<PatientTableView> patients = patientService.findViewAll();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/patients/add")
    public String addPatient(Model model, @RequestParam Optional<Long> ownerId, AddPatientDTO addPatientDTO){
        boolean hasOwner = ownerId.isPresent();
            List<OwnerDropDownView> owners = ownerService.findAll();
            model.addAttribute("owners", owners);
            model.addAttribute("hasOwner", hasOwner);
            if (hasOwner) {
                addPatientDTO.setOwnerId(ownerId.get());
            }
    return "patient-add";
    }
    @PostMapping("/patients/add")
    public String addPatient(@Valid AddPatientDTO addPatientDTO,
                             @RequestParam Optional<Long> ownerId,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        boolean hasOwner = ownerId.isPresent();
        List<OwnerDropDownView> owners = ownerService.findAll();
        Optional<AnimalEntity> optionalPatient = patientService.findByIdentificationNumber(addPatientDTO.getIdentificationNumber());
        if (bindingResult.hasErrors() || optionalPatient.isPresent()){
            redirectAttributes.addFlashAttribute("addPatientDTO", addPatientDTO);
            redirectAttributes.addFlashAttribute("owners", owners);
            redirectAttributes.addFlashAttribute("hasOwner", hasOwner);
            redirectAttributes.addFlashAttribute("patientExist", optionalPatient.isPresent());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPatientDTO",
                    bindingResult);
            return "redirect:/patients/add" + (hasOwner ? "?ownerId=" + ownerId.get().toString() : "");
        }
        AnimalEntity newPatient = new AnimalEntity()
                .setOwner(ownerService.findById(addPatientDTO.getOwnerId()))
                .setName(addPatientDTO.getName())
                .setAnimalType(addPatientDTO.getAnimalType())
                .setVaccinated(addPatientDTO.isVaccinated())
                .setBirthday(addPatientDTO.getBirthdate())
                .setIdentificationNumber(addPatientDTO.getIdentificationNumber())
                .setBreed(addPatientDTO.getBreed());

        patientService.save(newPatient);
        return "redirect:/patients/all";
    }
}
