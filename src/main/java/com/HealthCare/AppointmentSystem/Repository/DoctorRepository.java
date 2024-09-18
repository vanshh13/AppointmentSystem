package com.HealthCare.AppointmentSystem.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthCare.AppointmentSystem.Entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Long>{

}
