package com.HealthCare.AppointmentSystem.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthCare.AppointmentSystem.Entity.Department;
@Repository
public interface DepartmentRespository extends JpaRepository<Department, Long>{

}
