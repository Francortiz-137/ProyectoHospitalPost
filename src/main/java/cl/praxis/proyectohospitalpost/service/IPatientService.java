package cl.praxis.proyectohospitalpost.service;

import cl.praxis.proyectohospitalpost.entity.Patient;

import java.util.List;

public interface IPatientService {
    Patient addPatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
}
