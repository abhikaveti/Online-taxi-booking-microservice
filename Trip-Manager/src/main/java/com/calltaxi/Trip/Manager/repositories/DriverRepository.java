package com.calltaxi.Trip.Manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltaxi.Trip.Manager.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String>{

}
