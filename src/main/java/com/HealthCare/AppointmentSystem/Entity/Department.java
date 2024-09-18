package com.HealthCare.AppointmentSystem.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@JsonIgnoreType
@Table(name = "department")
public class Department {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Appointment> appointments;
    
    public Department(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department(Long id, String name, List<Doctor> doctor_id, List<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.appointments = appointments;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", appointments=" + appointments + "]";
	}

	public Department getDepartment() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}
