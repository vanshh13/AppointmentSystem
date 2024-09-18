package com.HealthCare.AppointmentSystem.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.HealthCare.AppointmentSystem.Entity.Patient;
import com.HealthCare.AppointmentSystem.Service.PatientService;

@RestController
@RequestMapping("/Patient")
public class Patientcontroller {
    private final PatientService patientService;

    @Autowired
    public Patientcontroller(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient existingPatient = patientService.getPatientById(id);
        
        if (existingPatient != null) {
            // Update patient details
            existingPatient.setName(patientDetails.getName());
            existingPatient.setAddress(patientDetails.getAddress());
            existingPatient.setMobile_no(patientDetails.getMobile_no());
            existingPatient.setBlood_group(patientDetails.getBlood_group());
            
            return patientService.updatePatient(existingPatient);
        } else {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
    }
}

