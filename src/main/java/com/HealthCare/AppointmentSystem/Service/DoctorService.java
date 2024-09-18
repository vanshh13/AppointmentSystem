package com.HealthCare.AppointmentSystem.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.AppointmentSystem.Entity.Appointment;
import com.HealthCare.AppointmentSystem.Entity.Department;
import com.HealthCare.AppointmentSystem.Entity.Doctor;
import com.HealthCare.AppointmentSystem.Repository.DepartmentRespository;
import com.HealthCare.AppointmentSystem.Repository.DoctorRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class DoctorService {
	private final DoctorRepository doctorRepository;
    private final DepartmentRespository departmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DepartmentRespository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor createDoctor(Doctor doctor, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                            .orElseThrow(() -> new RuntimeException("Department not found for ID: " + departmentId));
        
        doctor.setDepartment(department); // set the Department with the Doctor
        
        return doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
