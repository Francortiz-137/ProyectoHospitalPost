package cl.praxis.proyectohospitalpost.controller;

import cl.praxis.proyectohospitalpost.service.IPatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientsController {

    IPatientService patientService;

    public PatientsController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }
}
