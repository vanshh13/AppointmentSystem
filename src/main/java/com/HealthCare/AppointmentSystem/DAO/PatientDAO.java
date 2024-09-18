package com.HealthCare.AppointmentSystem.DAO;

import java.util.List;
import com.HealthCare.AppointmentSystem.Entity.*;

public interface PatientDAO {
	
	public List<Patient> getAllPatients();
	public Patient getPatientById(int theId);
	
	public Patient createPatient(Patient thePatient);
	
	public void deletePatient(int theId);
	
	public Patient updatePatient(Patient patient);
}
