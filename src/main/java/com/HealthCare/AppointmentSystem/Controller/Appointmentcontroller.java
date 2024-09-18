package com.HealthCare.AppointmentSystem.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.HealthCare.AppointmentSystem.Entity.Appointment;
import com.HealthCare.AppointmentSystem.Entity.Department;
import com.HealthCare.AppointmentSystem.Entity.Doctor;
import com.HealthCare.AppointmentSystem.Entity.Patient;
import com.HealthCare.AppointmentSystem.Service.AppointmentService;
import com.HealthCare.AppointmentSystem.Service.DepartmentService;
import com.HealthCare.AppointmentSystem.Service.DoctorService;
import com.HealthCare.AppointmentSystem.Service.PatientService;

//import jakarta.persistence.EntityManager;
@RestController
@RequestMapping("/Appointment")
public class Appointmentcontroller {
	@Autowired
//    private EntityManager entityManager; 

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DepartmentService departmentService;

    @Autowired
    public Appointmentcontroller(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, DepartmentService departmentService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentService.getAppointmentById(id);
        if (existingAppointment == null) {
            throw new RuntimeException("Appointment not found for ID: " + id);
        }

        existingAppointment.setType(updatedAppointment.getType());
        existingAppointment.setWard(updatedAppointment.getWard());

        // Retrieve and set department
        Department updatedDepartment = updatedAppointment.getDepartment();
        if (updatedDepartment != null && updatedDepartment.getId() != null) {
            Department department = departmentService.getDepartmentById(updatedDepartment.getId());
            if (department == null) {
                throw new RuntimeException("Department not found for given ID: " + updatedDepartment.getId());
            }
            existingAppointment.setDepartment(department);
        }

        // Retrieve and set doctor
        Doctor updatedDoctor = updatedAppointment.getDoctor();
        if (updatedDoctor != null && updatedDoctor.getId() != null) {
            Doctor doctor = doctorService.getDoctorById(updatedDoctor.getId());
            if (doctor == null) {
                throw new RuntimeException("Doctor not found for given ID: " + updatedDoctor.getId());
            }
            existingAppointment.setDoctor(doctor);
        }

        // Retrieve and set patient
        Patient updatedPatient = updatedAppointment.getPatient();
        if (updatedPatient != null && updatedPatient.getId() != null) {
            Patient patient = patientService.getPatientById(updatedPatient.getId());
            if (patient == null) {
                throw new RuntimeException("Patient not found for given ID: " + updatedPatient.getId());
            }
            existingAppointment.setPatient(patient);
        }

        return appointmentService.updateAppointment(existingAppointment);
    }
 
    @PostMapping
    @Transactional
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        try {
            if (appointmentRequest.getDoctor() == null || appointmentRequest.getPatient() == null || appointmentRequest.getDepartment() == null) {
                return new ResponseEntity<>("Doctor, Patient, and Department must be provided", HttpStatus.BAD_REQUEST);
            }

            Doctor doctor = doctorService.getDoctorById(appointmentRequest.getDoctor().getId());
            if (doctor == null) {
                return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
            }

            Patient patient = patientService.getPatientById(appointmentRequest.getPatient().getId());
            if (patient == null) {
                return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
            }

            Department department = departmentService.getDepartmentById(appointmentRequest.getDepartment().getId());
            if (department == null) {
                return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
            }

            Appointment appointment = new Appointment();
            appointment.setType(appointmentRequest.getType());
            appointment.setWard(appointmentRequest.getWard());
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setDepartment(department);

            appointmentService.createAppointment(appointment);

            return new ResponseEntity<>("Appointment created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create appointment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    static class AppointmentRequest {
        private String type;
        private String ward;
        private DoctorDto doctor;
        private PatientDto patient;
        private DepartmentDto department;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public DoctorDto getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorDto doctor) {
            this.doctor = doctor;
        }

        public PatientDto getPatient() {
            return patient;
        }

        public void setPatient(PatientDto patient) {
            this.patient = patient;
        }

        public DepartmentDto getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentDto department) {
            this.department = department;
        }
    }

    static class DoctorDto {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    static class PatientDto {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    static class DepartmentDto {
        private Long id;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}