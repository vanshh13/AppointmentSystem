package com.HealthCare.AppointmentSystem.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthCare.AppointmentSystem.Entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
