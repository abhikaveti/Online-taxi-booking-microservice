package com.calltaxi.Booking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calltaxi.Booking.entities.Ride;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public interface RideRepository extends JpaRepository<Ride, String>{

	/*
	 * public static List<Ride> getBookingDetailsByDriverId(String driverId){
	 * List<Ride> ridesofDriver; try { Query q =
	 * em.createNamedQuery("getBookingDetailsByDriverId");
	 * q.setParameter("driverId", driverId); ridesofDriver = q.getResultList();
	 * }catch(Exception e) { throw e; }
	 * 
	 * return ridesofDriver; }
	 */
	
	public List<Ride> findAllByDriverId(String driverId);
}
