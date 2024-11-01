package com.calltaxi.Trip.Manager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rides {
	private String carId;
	private String driverId;
	private String userId;
	private Double distance;
	private boolean isEnagagedInRide;
	private Double fare;
}
