package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.service.ManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/manipulations")
public class ManipulationController {

    private ManipulationService manipulationService;

    @Autowired
    public ManipulationController(ManipulationService manipulationService) {
        this.manipulationService = manipulationService;
    }

    @GetMapping("/add/{id}")
    public String addManipulation(Model model, @PathVariable Long id) {
        ManipulationAddDTO manipulationAddDTO = manipulationService.initializeManipulation(id);
        model.addAttribute("manipulationAddDTO",manipulationAddDTO);
        return "manipulations-add";
    }

    @GetMapping("/add//{id}/error")
    public String add(@PathVariable Long id) {
        return "manipulations-add";
    }

    @PostMapping("/add/{id}")
    public String add(@Valid ManipulationAddDTO manipulationAddDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      @PathVariable Long id,
                      @AuthenticationPrincipal UserDetails userDetails){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("manipulationAddDTO",manipulationAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.manipulationAddDTO", bindingResult);
            return "redirect:/add/" + id + "/error";
        }

        manipulationService.addManipulation(manipulationAddDTO,id,userDetails);

        return "redirect:/beehives/view/" + id;
    }
}
