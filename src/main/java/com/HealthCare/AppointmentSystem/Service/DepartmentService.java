package com.HealthCare.AppointmentSystem.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HealthCare.AppointmentSystem.Entity.Department;
import com.HealthCare.AppointmentSystem.Repository.DepartmentRespository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRespository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
