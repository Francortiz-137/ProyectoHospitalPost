package cl.praxis.proyectohospitalpost.repository;

import cl.praxis.proyectohospitalpost.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient,Long> {
}
