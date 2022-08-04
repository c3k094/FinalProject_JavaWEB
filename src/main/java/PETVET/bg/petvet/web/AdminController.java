package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AdminEditUserDTO;
import PETVET.bg.petvet.model.dto.EditPatientDTO;
import PETVET.bg.petvet.model.dto.UserEditDTO;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admins")
@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("adminEditUserDTO")
    public AdminEditUserDTO initUser(@PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            AdminEditUserDTO adminEditUserDTO = userService.findAdminEditUserDTO(id.get());
            return adminEditUserDTO;
        }
        return new AdminEditUserDTO();
    }

    @GetMapping("/users/all")
    public String allUsers(Model model){
        model.addAttribute("users", userService.findAllUsersAdminView());
        return "admin-users";
    }

    @GetMapping("/users/edit/{id}")
    public String editProfile(Model model, @PathVariable Long id) {

        model.addAttribute("user", userService.findAdminEditUserView(id));
        return "admin-editProfile";
    }

    @PutMapping("/users/edit/{id}")
    public String editProfile(AdminEditUserDTO adminEditUserDTO, @PathVariable Long id) {
         userService.adminUpdateProfile(adminEditUserDTO, id);


        return "redirect:/admins/users/all";
    }

    @DeleteMapping("/users/delete/{id}")
        public String deleteUser(@PathVariable Long id){
        userService.deleteByUserId(id);

        return "redirect:/";

    }
}
