package com.HealthCare.AppointmentSystem.Entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreType
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="mobile_no")
    private String mobile_no;
    
    private String type;
    
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("doctors")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnoreProperties("doctor_id")
    private List<Appointment> appointments;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String name, String mobile_no, String type, Department department,
            List<Appointment> appointments) {
        super();
        this.id = id;
        this.name = name;
        this.mobile_no = mobile_no;
        this.type = type;
        this.department = department;
        this.appointments = appointments;
    }

    // Getters and setters
    
    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + ", mobile_no=" + mobile_no + ", type=" + type + ", department="
                + department + ", appointments=" + appointments + "]";
    }

    public Long getDepartmentID() {
        if (this.department != null) {
            return this.department.getId();
        } else {
            return null;
        }
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

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDepartmentID(Long departmentID) {
        if (this.department == null) {
            this.department = new Department();
        }
        this.department.setId(departmentID);
    }
    
    public void addAppointment(Appointment appointment) {
        if (this.appointments == null) {
            this.appointments = new ArrayList<>();
        }
        this.appointments.add(appointment);
        appointment.setDoctor(this); // Set the doctor for the appointment
    }
}
