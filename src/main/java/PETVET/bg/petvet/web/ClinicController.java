package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.ClinicAddDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/clinics")
public class ClinicController {

    @ModelAttribute("clinicAddDTO")
    public ClinicAddDTO clinicAddDTO() {
        return new ClinicAddDTO();
    }
    @GetMapping("/add")
    public String addProduct() {
        return "clinic-add";
    }
    @PostMapping("/add")
    public String addClinic(@Valid ClinicAddDTO clinicAddDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("clinicAddDTO", clinicAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clinicAddDTO",bindingResult);
            return "redirect:/products/add";
        }
        return "redirect:/";
    }
}


