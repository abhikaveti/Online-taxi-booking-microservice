package com.calltaxi.Trip.Manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltaxi.Trip.Manager.DTO.Ride;
import com.calltaxi.Trip.Manager.DTO.Rides;
import com.calltaxi.Trip.Manager.services.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

	
	@Autowired
	private TripService tripService;
	
	@GetMapping("/getrides/{latitude}:{longitude}/{dropLatitude}:{dropLongitude}")
	public List<Rides> getAvailableRides(@PathVariable double latitude, @PathVariable double longitude,
										@PathVariable double dropLatitude, @PathVariable double dropLongitude){
		return tripService.getAvailableRides(latitude, longitude, dropLatitude, dropLongitude);
	}
	
	@PostMapping("/confirmride/{userId}")
	public String confirmRide(@RequestBody Ride ride, @PathVariable String userId) {
		return tripService.confirmRide(ride, userId);
	}
	
	@GetMapping("/getrides/best/{latitude}:{longitude}/{dropLatitude}:{dropLongitude}")
	public Rides getBestFare(@PathVariable double latitude, @PathVariable double longitude,
			@PathVariable double dropLatitude, @PathVariable double dropLongitude) {
		return tripService.getBestAvailableFare(latitude, longitude, dropLatitude, dropLongitude);
	}
	
	@PostMapping("/completeride/{rideid}")
	public void completeRide(@PathVariable String rideid) {
		tripService.completeRide(rideid);
	}
	
}
