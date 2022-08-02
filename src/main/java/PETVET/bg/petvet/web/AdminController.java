package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.dto.UserEditDTO;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admins")
@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String allUsers(Model model){
        model.addAttribute("users", userService.findAllUsersAdminView());
        return "admin-users";
    }

    @GetMapping("/edit/{id}")
    public String editProfile(Model model, @PathVariable Long id) {
        UserEditDTO userEditDTO = userService.getEditDetailsById(id);
        model.addAttribute("userEditDTO", userEditDTO);
        return "admin-editProfile";
    }
}
