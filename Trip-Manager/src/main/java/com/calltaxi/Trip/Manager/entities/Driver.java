package com.calltaxi.Trip.Manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
	
	@Id
	@Column(name="driverid") private String driverId;
	@Column(name="drivername") private String driverName;
	@Column(name="driverphone") private String driverPhone;
	
	@OneToOne
	@JoinColumn(name = "carid", referencedColumnName = "carid")
	private Car car;
}
