package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.AddOwnerDTO;
import PETVET.bg.petvet.model.dto.UserRegisterDTO;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class OwnerController {
    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @ModelAttribute("addOwnerModel")
    public void initUserModel(Model model) {
        model.addAttribute("addOwnerModel", new AddOwnerDTO());
    }

    @GetMapping("/owners/add")
    public String addOwner(){
        return "owner-add";
    }

    @PostMapping("/owners/add")
    public String addOwner(@Valid AddOwnerDTO addOwnerModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        Optional<OwnerEntity> optionalOwnerByEmail = ownerService.findByEmail(addOwnerModel.getEmail());
        Optional<OwnerEntity> optionalOwnerByPhone = ownerService.findByPhoneNumber(addOwnerModel.getPhoneNumber());
        if (bindingResult.hasErrors() || optionalOwnerByEmail.isPresent() || optionalOwnerByPhone.isPresent()) {
            redirectAttributes.addFlashAttribute("addOwnerModel", addOwnerModel);
            redirectAttributes.addFlashAttribute("emailTaken", optionalOwnerByEmail.isPresent());
            redirectAttributes.addFlashAttribute("phoneTaken", optionalOwnerByPhone.isPresent());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOwnerModel",
                    bindingResult);
            return "redirect:/owners/add";
        }

    ownerService.addOwner(addOwnerModel);
        return "redirect:/owners/all";

    }
}
