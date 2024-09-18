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
@Table(name = "patient")
public class Patient {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "name")
	    private String name;

	    @Column(name = "address")
	    private String address;

	    @Column(name = "mobile_no")
	    private String mobile_no;

	    @Column(name = "blood_group")
	    private String blood_group;
	    
	    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    private List<Appointment> appointments;

	public Patient(Long id, String name, String address, String mobile_no, String blood_group,
			List<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile_no = mobile_no;
		this.blood_group = blood_group;
		
	}


	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Patient(Long id, String name, String address, String mobile_no, String blood_group) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile_no = mobile_no;
		this.blood_group = blood_group;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

//	public Patient(List<Appointment> appointments) {
//		super();
//		this.appointments = appointments;
//	}

	
//	@Override
//	public String toString() {
//		return "Patient [id=" + id + ", name=" + name + ", address=" + address + ", mobile_no=" + mobile_no
//				+ ", blood_group=" + blood_group +  "]";
//	}

	public Patient getPatient() {
        return this;
    }


	public List<Appointment> getAppointments() {
		return appointments;
	}


	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", address=" + address + ", mobile_no=" + mobile_no
				+ ", blood_group=" + blood_group + ", appointments=" + appointments + "]";
	}
}
