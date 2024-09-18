package com.HealthCare.AppointmentSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.HealthCare.AppointmentSystem.Entity.Department;
import com.HealthCare.AppointmentSystem.Entity.Doctor;
import com.HealthCare.AppointmentSystem.Repository.AppointmentRepository;
import com.HealthCare.AppointmentSystem.Entity.Appointment;
import com.HealthCare.AppointmentSystem.Service.DepartmentService;
import com.HealthCare.AppointmentSystem.Service.DoctorService;

@RestController
@RequestMapping("/Doctor")
public class Doctorcontroller {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    public Doctorcontroller(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        Long departmentId = doctor.getDepartmentID();
        if (departmentId == null) {
            throw new RuntimeException("Department ID is required.");
        }

        // Fetch the department
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            throw new RuntimeException("Department not found for given ID: " + departmentId);
        }

        // Create a new list to managed appointments
//        List<Appointment> managedAppointments = new ArrayList<>();
//
//        for (Appointment appointment : doctor.getAppointments()) {
//            if (appointment.getId() != null) {
//                Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
//                if (optionalAppointment.isPresent()) {
//                    // If the appointment exists, add it to the managed list
//                    managedAppointments.add(optionalAppointment.get());
//                } else {
//                    throw new RuntimeException("Appointment not found for ID: " + appointment.getId());
//                }
//            } else {
//                managedAppointments.add(appointment);
//            }
//        }
//
//    
//        doctor.setAppointments(managedAppointments);

        // Set the department to the doctor
        doctor.setDepartment(department);

        // Create the doctor
        Doctor savedDoctor = doctorService.createDoctor(doctor,departmentId);

        return savedDoctor;
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor == null) {
            throw new RuntimeException("Doctor not found for ID: " + id);
        }

        existingDoctor.setName(doctor.getName());
        existingDoctor.setMobile_no(doctor.getMobile_no());
        existingDoctor.setType(doctor.getType());
        existingDoctor.setAppointments(doctor.getAppointments());

        Long departmentId = doctor.getDepartmentID();
        if (departmentId != null) {
            Department department = departmentService.getDepartmentById(departmentId);
            if (department != null) {
                existingDoctor.setDepartment(department);
            } else {
                // Handle not found
                throw new RuntimeException("Department not found for given ID: " + departmentId);
            }
        } else {
            existingDoctor.setDepartment(null); 
        }

        return doctorService.updateDoctor(existingDoctor);
    }

}
