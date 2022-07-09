package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.UserRegisterDTO;
import PETVET.bg.petvet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @ModelAttribute("userModel")
  public void initUserModel(Model model) {
    model.addAttribute("userModel", new UserRegisterDTO());
  }

  @GetMapping("/users/register")
  public String register() {
    return "register";
  }


  @PostMapping("/users/register")
  public String register(@Valid UserRegisterDTO userModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
    boolean passwordsDoNotMatch = !userModel.getPassword().equals(userModel.getConfirmPassword());
    if (bindingResult.hasErrors() || passwordsDoNotMatch){
      redirectAttributes.addFlashAttribute("userModel", userModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
      redirectAttributes.addFlashAttribute("passwordsDoNotMatch", passwordsDoNotMatch);
      return "redirect:/users/register";
    }
    if(userService.findByEmail(userModel.getEmail()).isPresent()){
      redirectAttributes.addFlashAttribute("userModel", userModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
      redirectAttributes.addFlashAttribute("dupeEmail", true);
      return "redirect:/users/register";
    }

    userService.registerAndLogin(userModel);
    return "redirect:/";
  }
}
