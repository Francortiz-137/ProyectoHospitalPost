package cl.praxis.proyectohospitalpost.controller;

import cl.praxis.proyectohospitalpost.entity.Patient;
import cl.praxis.proyectohospitalpost.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientsController implements CommandLineRunner {

    IPatientService patientService;
    private final static Logger LOG = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    public PatientsController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String patients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        LOG.info("Enviando lista de pacientes");
        return "patients";
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient-details";
    }

    @PostMapping("/save")
    public String savePatient( @ModelAttribute("patient") Patient patient,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgError", "Datos erroneos");
        }else{
            patientService.addPatient(patient);
            List<Patient> patients = patientService.getAllPatients();
            model.addAttribute("patients", patients);
        }
        return "patients";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id,
                                @ModelAttribute("patient") Patient patientUpdated,
                                BindingResult result,
                                Model model) {
        Patient patient = patientService.getPatientById(id);

        patient.setFirstName(patientUpdated.getFirstName());
        patient.setLastName(patientUpdated.getLastName());
        patient.setEmail(patientUpdated.getEmail());
        patient.setPhone(patientUpdated.getPhone());
        patientService.updatePatient(patient);
        LOG.info("Actualizando paciente");
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        LOG.warn("Eliminando paciente con id {}", id);
        patientService.deletePatientById(id);
        return "redirect:/patients";
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Iniciando controlador de pacientes...");
    }
}
