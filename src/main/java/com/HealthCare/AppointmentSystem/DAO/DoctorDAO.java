package com.HealthCare.AppointmentSystem.DAO;

import java.util.List;

import com.HealthCare.AppointmentSystem.Entity.Doctor;

public interface DoctorDAO {
	public List<Doctor> getAllDoctors();
	public Doctor getDoctorById(int theId);
	
	public Doctor createDoctor(Doctor theDoctor);
	
	public void deleteDoctor(int theId);
	
    Doctor updateDoctor(Doctor doctor);

}
