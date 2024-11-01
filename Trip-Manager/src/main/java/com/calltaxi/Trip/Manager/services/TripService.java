package com.calltaxi.Trip.Manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.calltaxi.Trip.Manager.DTO.Rides;
import com.calltaxi.Trip.Manager.DTO.Ride;
import com.calltaxi.Trip.Manager.entities.Driver;
import com.calltaxi.Trip.Manager.repositories.DriverRepository;



//cars
//drivers
// 

@Service
public class TripService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private KafkaMessageHandler kafkaHandler;
	
	@Value("${bookingsserviceendpoint}")
	private String bookingService;
	
	@Autowired
	private RestOperations restOps;
	
	public List<Rides> getAvailableRides(double pickupLat, double pickupLong, double dropLat, double dropLong){
		List<Driver> drivers = null;
		List<Rides> availableRides = new ArrayList<Rides>();
		
		try {
			drivers = driverRepository.findAll();
			for(Driver driver : drivers) {
				System.out.println(bookingService+"/bookings/getbookings/"+driver.getDriverId());
				Ride latest = restOps.exchange(bookingService+"/bookings/getbookings/"+driver.getDriverId(),HttpMethod.GET, null, Ride.class).getBody();
				Rides ride = new Rides();
				if(latest == null) {
					double distance = getDistance(pickupLat, pickupLong, 0, 0);
					ride.setDistance(distance);
					ride.setCarId(driver.getCar().getCarId());
					ride.setDriverId(driver.getDriverId());
					ride.setEnagagedInRide(false);
					ride.setFare(calculateFare(getDistance(pickupLat, pickupLong, dropLat, dropLong)));
					availableRides.add(ride);
				}else if(latest.isRideCompleted()) {
					double distance = getDistance(pickupLat, pickupLong, latest.getDropLat(), latest.getDropLong());
					ride.setDistance(distance);
					ride.setCarId(latest.getCarId());
					ride.setDriverId(latest.getDriverId());
					ride.setEnagagedInRide(false);
					ride.setFare(calculateFare(getDistance(pickupLat, pickupLong, dropLat, dropLong)));
					availableRides.add(ride);
				}
			}
		}catch(Exception e) {
			throw e;
		}
		return availableRides;
	}
	
	public Rides getBestAvailableFare(double pickupLat, double pickupLong, double dropLat, double dropLong) {
		
		List<Rides> availableRides = new ArrayList<>();
		Optional<Rides> res = null;
		try {
			availableRides = getAvailableRides(pickupLat, pickupLong, dropLat, dropLong);
			res = availableRides.stream().min((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));
		}catch(Exception e) {
			throw e;
		}
		return res.get();
	}
	
	public String confirmRide(Ride ride, String userId) {
		String rideid = null;
		try {
			rideid = UUID.randomUUID().toString();
			ride.setRideId(rideid);
			ride.setRideStarted(true);
			kafkaHandler.sendMessage("StartRide",ride);
		}catch(Exception e) {
			throw e;
		}
		return rideid;
	}
	
	private double getDistance(double pickupLat, double pickupLong, double dropLat, double dropLong) {
		return Math.sqrt(Math.pow(dropLat-pickupLat, 2)+Math.pow(dropLong-pickupLong, 2));
	}
	
	private double calculateFare(double distance) {
		return Math.PI*distance;
	}

	public void completeRide(String rideid) {
		Ride ride=null;
		try {
			ride = restOps.exchange(bookingService+"/bookings/getridedetails/"+rideid, HttpMethod.GET, null, Ride.class).getBody();
			ride.setRideCompleted(true);
			kafkaHandler.sendMessage("completeRide", ride);
		}catch(Exception e) {
			throw e;
		}
		
	}

}
