package PETVET.bg.petvet.web;

import PETVET.bg.petvet.service.ClinicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private ClinicService clinicService;

    @Autowired
    public HomeController( ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/")
    public String home(Principal principal) {
        if (principal != null) {
            return "home";
        }
        return "index";
    }
}
