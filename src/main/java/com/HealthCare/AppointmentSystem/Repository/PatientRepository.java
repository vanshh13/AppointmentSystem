package com.HealthCare.AppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthCare.AppointmentSystem.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long>{

}
