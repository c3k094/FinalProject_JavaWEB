package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.exception.NotFoundException;
import PETVET.bg.petvet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    // NOTE: This should be post mapping!
    @PostMapping("/users/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        try {
            if (userService.isBlocked(userName)) {
                redirectAttributes.addFlashAttribute("deActivated", true);
            } else {
                redirectAttributes.addFlashAttribute("bad_credentials", true);
            }

        } catch (NotFoundException nfe) {
            redirectAttributes.addFlashAttribute("bad_credentials", true);
        }

        return "redirect:/users/login";
    }

}
