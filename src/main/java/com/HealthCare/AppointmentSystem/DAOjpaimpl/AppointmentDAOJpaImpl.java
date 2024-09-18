package com.HealthCare.AppointmentSystem.DAOjpaimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import com.HealthCare.AppointmentSystem.DAO.AppointmentDAO;
import com.HealthCare.AppointmentSystem.Entity.Appointment;
import jakarta.persistence.TypedQuery;


public class AppointmentDAOJpaImpl implements AppointmentDAO {
	
	private final EntityManager entityManager;

    @Autowired
    public AppointmentDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	@Override
    public List<Appointment> getAllAppointments() {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a", Appointment.class);
        return query.getResultList();
    }
	@Override
    public Appointment getAppointmentById(int theId) {
        return entityManager.find(Appointment.class, theId);
    }
	@Override
	@Transactional
    public Appointment createAppointment(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }
	@Override
	@Transactional
    public void deleteAppointment(int id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            entityManager.remove(appointment);
        }
    }
	@Override
    @Transactional
    public Appointment updateAppointment(Appointment appointment) {
        return entityManager.merge(appointment);
    }
}
