package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.UserLoginDTO;
import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("loginUserDTO")
    public UserLoginDTO UserLoginDTO() {
        return new UserLoginDTO();
    }

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginUserDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserDTO",bindingResult);
            return "redirect:/users/login";
        }
        Optional<UserEntity> optionalUser = userService.findByEmail(userLoginDTO.getEmail());

        if(optionalUser.isEmpty()){
            redirectAttributes.addFlashAttribute("loginUserDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("mismatchingData",true);
            return "redirect:/users/login";
        } else if(optionalUser.isPresent()){
            boolean isPasswordCorrect = passwordEncoder.matches(userLoginDTO.getPassword(), optionalUser.get().getPassword());
            if (!isPasswordCorrect){
                redirectAttributes.addFlashAttribute("loginUserDTO", userLoginDTO);
                redirectAttributes.addFlashAttribute("mismatchingData",true);
                return "redirect:/users/login";
            }
        }

        userService.login(userLoginDTO);
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}