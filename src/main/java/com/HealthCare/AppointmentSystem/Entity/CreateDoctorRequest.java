package com.HealthCare.AppointmentSystem.Entity;

public class CreateDoctorRequest {
    private Doctor doctor;
    private Appointment appointment;

    // Constructors
    public CreateDoctorRequest() {
    }

    public CreateDoctorRequest(Doctor doctor, Appointment appointment) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor object cannot be null");
        }
        this.doctor = doctor;
        this.appointment = appointment;
    }

    // Getters and setters
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor object cannot be null");
        }
        this.doctor = doctor;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
