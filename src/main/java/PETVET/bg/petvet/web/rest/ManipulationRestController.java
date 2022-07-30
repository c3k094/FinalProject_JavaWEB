package PETVET.bg.petvet.web.rest;


import PETVET.bg.petvet.model.view.ManipulationTableView;
import PETVET.bg.petvet.service.ManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManipulationRestController {

    private ManipulationService manipulationService;


    @Autowired
    public ManipulationRestController(ManipulationService manipulationService) {
        this.manipulationService = manipulationService;
    }

    @GetMapping("/{patientId}/manipulations")
    public ResponseEntity<List<ManipulationTableView>> getManipulations(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(manipulationService.getAllManipulationsForPatient(patientId));
    }

}
