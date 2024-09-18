package com.HealthCare.AppointmentSystem.DAO;

import java.util.List;

import com.HealthCare.AppointmentSystem.Entity.Appointment;

public interface AppointmentDAO {
	public List<Appointment> getAllAppointments();
	public Appointment getAppointmentById(int theId);
	
	public Appointment createAppointment(Appointment theAppointment);
	
	public void deleteAppointment(int theId);
    Appointment updateAppointment(Appointment appointment);

}
