package com.calltaxi.Booking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NamedQuery(name="getBookingDetailsByDriverId", query="select ride from Ride ride where driverid = :driverId")
public class Ride extends Object{
	
	@Id
	@Column(name="rideid") private String rideId;
	@Column(name="carid") private String carId;
	@Column(name="driverid") private  String driverId;
	@Column(name="userid") private String userId;
	@Column(name="isridecompleted") private boolean isRideCompleted;
	@Column(name="timestamp") private String  timestamp;
	@Column(name="droplat") private double dropLat;
	@Column(name="droplong") private double dropLong;
	@Column(name="pickuplat") private double pickupLat;
	@Column(name="pickuplong") private double pickupLong;
	@Column(name="fare") private double fare;
	@Column(name="isridestarted") private boolean isRideStarted;
}
