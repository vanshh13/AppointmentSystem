package com.HealthCare.AppointmentSystem.DAOjpaimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HealthCare.AppointmentSystem.DAO.PatientDAO;
import com.HealthCare.AppointmentSystem.Entity.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PatientDAOJpaImpl implements PatientDAO {

	private final EntityManager entityManager;

    @Autowired
    public PatientDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	@Override
    public List<Patient> getAllPatients() {
        TypedQuery<Patient> query = entityManager.createQuery("SELECT a FROM Patient a", Patient.class);
        return query.getResultList();
    }
	@Override
    public Patient getPatientById(int theId) {
        return entityManager.find(Patient.class, theId);
    }
	@Override
	@Transactional
    public Patient createPatient(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }
	@Override
	@Transactional
    public void deletePatient(int id) {
		Patient patient = getPatientById(id);
        if (patient != null) {
            entityManager.remove(patient);
        }
    }
	
	@Override
    @Transactional
    public Patient updatePatient(Patient patient) {
        return entityManager.merge(patient);
    }
}
