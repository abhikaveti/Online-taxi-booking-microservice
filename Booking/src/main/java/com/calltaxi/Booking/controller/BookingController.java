package com.calltaxi.Booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltaxi.Booking.entities.Ride;
import com.calltaxi.Booking.services.BookingsService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	
	@Autowired
	private BookingsService bookingsService;
	
	@GetMapping("/getbookings/{driverId}")
	public Ride getLatestBookingDetailsByDriverId(@PathVariable String driverId){
		return bookingsService.getActiveBookingsOfDriver(driverId);
	}
	
	@GetMapping("/getridedetails/{rideid}")
	public Ride getRideDetails(@PathVariable String rideid) {
		return bookingsService.getRideDetails(rideid);
	}
}
