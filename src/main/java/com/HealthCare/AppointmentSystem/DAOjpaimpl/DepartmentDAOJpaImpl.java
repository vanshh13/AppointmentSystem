package com.HealthCare.AppointmentSystem.DAOjpaimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HealthCare.AppointmentSystem.DAO.DepartmentDAO;
import com.HealthCare.AppointmentSystem.Entity.Department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public class DepartmentDAOJpaImpl implements DepartmentDAO {

	private final EntityManager entityManager;

    @Autowired
    public DepartmentDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	@Override
    public List<Department> getAllDepartments() {
        TypedQuery<Department> query = entityManager.createQuery("SELECT a FROM Department a", Department.class);
        return query.getResultList();
    }
	@Override
    public Department getDepartmentById(int theId) {
        return entityManager.find(Department.class, theId);
    }
	@Override
	@Transactional
    public Department createDepartment(Department department) {
        entityManager.persist(department);
        return department;
    }
	@Override
	@Transactional
    public void deleteDepartment(int id) {
		Department department = getDepartmentById(id);
        if (department != null) {
            entityManager.remove(department);
        }
    }
	
	 @Override
	    @Transactional
	    public Department updateDepartment(Department department) {
	        return entityManager.merge(department);
	    }
}
