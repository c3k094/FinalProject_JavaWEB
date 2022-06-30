package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.user.CurrentUser;
import PETVET.bg.petvet.service.ClinicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private ClinicService clinicService;

    @Autowired
    public HomeController(CurrentUser currentUser, ClinicService clinicService) {
        this.currentUser = currentUser;
        this.clinicService = clinicService;
    }

    @GetMapping()
    public String home(Model model) {
        if (currentUser.isGuest()){
            return "index";
        }

        return "home";
    }
}
