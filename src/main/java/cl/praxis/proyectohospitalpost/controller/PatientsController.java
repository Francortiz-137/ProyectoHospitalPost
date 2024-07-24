package cl.praxis.proyectohospitalpost.controller;

import cl.praxis.proyectohospitalpost.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientsController implements CommandLineRunner {

    IPatientService patientService;
    private final static Logger LOG = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    public PatientsController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        LOG.info("Enviando lista de pacientes");
        return "patients";
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Iniciando controlador de pacientes...");
    }
}
