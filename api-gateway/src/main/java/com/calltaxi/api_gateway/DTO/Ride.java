package com.calltaxi.api_gateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride extends Object{
	private String rideId;
	private String carId;
	private  String driverId;
	private String userId;
	private boolean isRideStarted;
	private boolean isRideCompleted;
	private String  timestamp;
	private double dropLat;
	private double dropLong;
	private double pickupLat;
	private double pickupLong;
	private double fare;
}
