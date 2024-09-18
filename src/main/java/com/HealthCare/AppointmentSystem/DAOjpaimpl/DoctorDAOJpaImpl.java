package com.HealthCare.AppointmentSystem.DAOjpaimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HealthCare.AppointmentSystem.DAO.DoctorDAO;
import com.HealthCare.AppointmentSystem.Entity.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DoctorDAOJpaImpl implements DoctorDAO {
	private final EntityManager entityManager;

    @Autowired
    public DoctorDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	@Override
    public List<Doctor> getAllDoctors() {
        TypedQuery<Doctor> query = entityManager.createQuery("SELECT a FROM Doctor a", Doctor.class);
        return query.getResultList();
    }
	
	@Override
    public Doctor getDoctorById(int theId) {
        return entityManager.find(Doctor.class, theId);
    }
	@Override
	@Transactional
    public Doctor createDoctor(Doctor doctor) {
        entityManager.persist(doctor);
        return doctor;
    }
	@Override
	@Transactional
    public void deleteDoctor(int id) {
		Doctor appointment = getDoctorById(id);
        if (appointment != null) {
            entityManager.remove(appointment);
        }
    }
	
	@Override
    @Transactional
    public Doctor updateDoctor(Doctor doctor) {
        return entityManager.merge(doctor);
    }

}
