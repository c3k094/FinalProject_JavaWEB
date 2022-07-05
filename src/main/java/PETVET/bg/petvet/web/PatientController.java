package PETVET.bg.petvet.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {


@GetMapping("/patients/add")
    public String addPatient(){
    return "patient-add";
}
}
