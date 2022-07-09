package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AddOwnerDTO;
import PETVET.bg.petvet.model.dto.AddPatientDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatientController {

    @ModelAttribute("addPatientModel")
    public void initUserModel(Model model) {
        model.addAttribute("addPatientModel", new AddPatientDTO());
    }

@GetMapping("/patients/add")
    public String addPatient(){
    return "patient-add";
}
}
