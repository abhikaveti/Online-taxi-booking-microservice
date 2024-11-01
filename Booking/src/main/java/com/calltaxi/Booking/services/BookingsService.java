package com.calltaxi.Booking.services;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.calltaxi.Booking.entities.Ride;
import com.calltaxi.Booking.repositories.RideRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookingsService {
	
	@Autowired
	private RideRepository rideRepository;
	
	@Value("${kafka.topic.name}")
	private String topicName;
	
	public Ride getActiveBookingsOfDriver(String driverId){
		
		Ride latestBooking = null;
		
		try {
			List<Ride> bookingsByDriverId = rideRepository.findAllByDriverId(driverId);
			if(bookingsByDriverId.isEmpty()) return null;
			bookingsByDriverId.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
			latestBooking = bookingsByDriverId.get(0);
			
		}catch(Exception e) {
			throw e;
		}
		return latestBooking;
	}
	
	@KafkaListener(topics = "booking-details", groupId="bookings-1")
	public void getKafkaMessage(ConsumerRecord<String, String> message) {
		
		try {
			Ride ride = new ObjectMapper().readValue(message.value(),Ride.class);
			if("completeRide".equalsIgnoreCase(message.key())) ride.setRideCompleted(true);
			rideRepository.save(ride);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
//		rideRepository.save(message.value());
	}
	/*
	 * private class RideComparator implements Comparator<Ride>{
	 * 
	 * @Override public int compare(Ride o1, Ride o2) { return
	 * o2.getTimestamp().compareTo(o1.getTimestamp()); }}
	 */

	public Ride getRideDetails(String rideid) {
		return rideRepository.findById(rideid).get();
	}
}
