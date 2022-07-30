package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AddPatientDTO;
import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.entity.AnimalEntity;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.model.view.PatientDetailsView;
import PETVET.bg.petvet.model.view.PatientTableView;
import PETVET.bg.petvet.service.OwnerService;
import PETVET.bg.petvet.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    private OwnerService ownerService;
    private PatientService patientService;
    private ModelMapper modelMapper;

    @Autowired
    public PatientController(OwnerService ownerService, PatientService patientService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("addPatientDTO")
    public void initPatientModel(Model model) {
        model.addAttribute("addPatientDTO", new AddPatientDTO());
    }

    @GetMapping("/patients/view/{id}")
    public String viewPatient(Model model, @PathVariable Long id){
        PatientDetailsView patientDetails = patientService.findPatientDetailsById(id);
        model.addAttribute("patientDetails", patientDetails);
        return "patient";
    }

    @GetMapping("/patients/all")
    public String allPatients(Model model,@PageableDefault(
            sort = "owner.firstName",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 7) Pageable pageable) {

        model.addAttribute("patients", patientService.findViewAll(pageable));

        return "patients";
    }
       // List<PatientTableView> patients = patientService.findViewAll();
        //model.addAttribute("patients", patients);
        //return "patients";
    @GetMapping("/patients/delete/{id}")
    public String delete(@PathVariable Long id){
        patientService.deleteById(id);
        return "redirect:/patients/all";
    }

    @GetMapping("/patients/edit/{id}")
    public String editPatient(Model model, @PathVariable Long id) {
        List<OwnerDropDownView> owners = ownerService.findAll();
        EditPatientDTO editPatientDTO = patientService.getEditPatientDTOById(id);
        editPatientDTO.setId(id);
        String identID = patientService.findIdentificationNumberById(id);
        model.addAttribute("editPatientDTO",editPatientDTO);
        model.addAttribute("owners", owners);
        model.addAttribute("identID", identID);
        return "patient-edit";
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

    @GetMapping("/patients/add/error")
    public String addError(){
        return "patient-add";
    }

    @GetMapping("/patients/edit/{id}/error")
    public String editError(Model model, @PathVariable Long id){
        String identID = patientService.findIdentificationNumberById(id);

        model.addAttribute("identID", identID);
        return "patient-edit";
    }

    @PostMapping("/patients/edit/{id}")
    public String addPatient(@Valid EditPatientDTO editPatientDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable Long id
    ){
        List<OwnerDropDownView> owners = ownerService.findAll();
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editPatientDTO", editPatientDTO);
            redirectAttributes.addFlashAttribute("owners", owners);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editPatientDTO",
                    bindingResult);
            return "redirect:/patients/edit/" + id.toString() + "/error";
        }
        patientService.updatePatient(editPatientDTO);
        return "redirect:/patients/view/" + id;
    }

    @PostMapping("/patients/add")
    public String addPatient(@Valid AddPatientDTO addPatientDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam Optional<Long> ownerId
                             ){

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
            return "redirect:/patients/add/error" + (hasOwner ? "?ownerId=" + ownerId.get().toString() : "");
        }
        AnimalEntity newPatient = new AnimalEntity()
                .setOwner(ownerService.findById(addPatientDTO.getOwnerId()))
                .setName(addPatientDTO.getName())
                .setAnimalType(addPatientDTO.getAnimalType())
                .setVaccinated(addPatientDTO.isVaccinated())
                .setVaccine(addPatientDTO.getVaccine())
                .setVaccinationDate(addPatientDTO.getVaccinationDate())
                .setBirthday(addPatientDTO.getBirthdate())
                .setIdentificationNumber(addPatientDTO.getIdentificationNumber())
                .setBreed(addPatientDTO.getBreed())
                .setDewormed(addPatientDTO.isDewormed())
                .setDewormingType(addPatientDTO.getDewormingType())
                .setDewormingDate(addPatientDTO.getDewormingDate())
                .setCastrated(addPatientDTO.isCastrated())
                ;


        patientService.save(newPatient);
        return "redirect:/patients/all";
    }
}
