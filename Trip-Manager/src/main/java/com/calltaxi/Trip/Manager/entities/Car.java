package com.calltaxi.Trip.Manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	@Id
	@Column(name="carid") private String carId;
	@Column(name="carname") private String carName;
	@Column(name="droplat") private double latitude;
	@Column(name="droplong") private double longitude;
	
	@OneToOne(mappedBy = "car")
	private Driver driver;
}
