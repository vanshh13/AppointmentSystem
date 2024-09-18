	package com.HealthCare.AppointmentSystem.Entity;
	
	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import com.fasterxml.jackson.annotation.JsonIgnoreType;
	import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	@Entity
	@JsonIgnoreType
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Appointment {
		@Id
		@Column(name="id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name="type")
		String type;
		@Column(name="ward")
		String ward;
		@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	    @JoinColumn(name = "patient_id")
	    private Patient patient;
	
		@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,optional = false)
		@JoinColumn(name = "doctor_id")
		private Doctor doctor;

		@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,optional = false)
		@JoinColumn(name = "department_id")
		private Department department;
		
		public Appointment() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Appointment(Long id, String type, String ward,Patient  patient_id, Doctor doctor_id,Department department) {
			super();
			this.id = id;
			this.type = type;
			this.ward = ward;
			this.patient = patient_id;
			this.doctor = doctor_id;
			this.department = department;
		}
		public Appointment( String type, String ward, Doctor doctor_id,Patient  patient_id,Department department) {
			super();
			this.type = type;
			this.ward = ward;
			this.patient = patient_id;
			this.doctor = doctor_id;
			this.department = department;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		
		public Appointment(Department department) {
			super();
			this.department = department;
		}
		public Doctor getDoctor() {
		    return doctor;
		}

		public void setDoctor(Doctor doctor) {
		    this.doctor = doctor;
		}



		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient_id) {
			this.patient = patient_id;
		}
		public Department getDepartment() {
			return department;
		}
		public void setDepartment(Department department) {
			this.department = department;
		}
		
		@Override
		public String toString() {
			return "Appointment [id=" + id + ", type=" + type + ", ward=" + ward + ", patient_id=" + patient
					+ ", doctor_id=" + doctor + ", department=" + department + "]";
		}
		public Appointment getAppointment() {
			// TODO Auto-generated method stub
			return this;
		}
		
	}
