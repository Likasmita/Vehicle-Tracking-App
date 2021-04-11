package com.vehicle.violation.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vehicle.violation.tracker.entities.VehicleViolation;

@Repository
public interface ViolationRepository extends CrudRepository<VehicleViolation, Integer> {
	
	 @Query("SELECT v FROM VehicleViolation v WHERE v.vehicleNumber= :vehicleNumber and v.penaltyDone= 0")
	List<VehicleViolation> findAll(@Param("vehicleNumber") String vehicleNumber);
}
