package PETVET.bg.petvet.web;

import PETVET.bg.petvet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
