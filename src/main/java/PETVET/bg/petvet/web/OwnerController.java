package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.dto.*;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.view.OwnerDetailsView;
import PETVET.bg.petvet.model.view.OwnerDropDownView;
import PETVET.bg.petvet.model.view.OwnerTableView;
import PETVET.bg.petvet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
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

    @DeleteMapping("/owners/delete/{id}")
    public String delete(@PathVariable Long id){
        ownerService.deleteById(id);
        return "redirect:/owners/all";
    }

    @GetMapping("/owners/edit/{id}")
    public String editPatient(Model model, @PathVariable Long id) {
        EditOwnerDTO editOwnerDTO = ownerService.getEditOwnerDTOById(id);
        editOwnerDTO.setId(id);
        model.addAttribute("editOwnerDTO",editOwnerDTO);
        return "owner-edit";
    }

    @GetMapping("/owners/edit/{id}/error")
    public String editError(){
        return "owner-edit";
    }

    @PutMapping("/owners/edit/{id}")
    public String editPatient(@Valid EditOwnerDTO editOwnerDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable Long id
    ){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editOwnerDTO", editOwnerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editOwnerDTO",
                    bindingResult);
            return "redirect:/owners/edit/" + id.toString() + "/error";
        }
        ownerService.updateOwner(editOwnerDTO);
        return "redirect:/owners/view/" + id;
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

    @GetMapping("/owners/all")
    public String allOwners(@Valid SearchOwnerDTO searchOwnerDTO,BindingResult bindingResult, Model model, @PageableDefault(
            sort = "firstName",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 7) Pageable pageable){

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOwnerDTO", searchOwnerDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOwnerDTO",
                    bindingResult);
            return "owners";
        }
        if (!model.containsAttribute("searchOwnerDTO")) {
            model.addAttribute("searchOwnerDTO", searchOwnerDTO);
        }

        if (!searchOwnerDTO.isEmpty()) {
            model.addAttribute("owners", ownerService.searchOwner(searchOwnerDTO, pageable));
        } else {
            model.addAttribute("owners", ownerService.findViewAll(pageable));

        }


        return "owners";
    }

    @GetMapping("/owners/view/{id}")
        public String viewOwner(Model model, @PathVariable Long id){
        OwnerDetailsView ownerDetails = ownerService.findOwnerDetailsById(id);
        model.addAttribute("ownerDetails",ownerDetails);
        return "owner";
    }
}
