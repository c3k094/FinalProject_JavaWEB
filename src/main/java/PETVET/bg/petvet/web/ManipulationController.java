package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.service.ManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
