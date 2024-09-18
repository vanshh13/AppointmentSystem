package com.HealthCare.AppointmentSystem.DAO;

import java.util.List;

import com.HealthCare.AppointmentSystem.Entity.Department;

public interface DepartmentDAO {
	public List<Department> getAllDepartments();
	public Department getDepartmentById(int theId);
	
	public Department createDepartment(Department theDepartment);
	
	public void deleteDepartment(int theId);
	
    Department updateDepartment(Department department);

}
