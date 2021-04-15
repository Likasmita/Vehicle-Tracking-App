package com.vehicle.violation.tracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vehicle.violation.tracker.entities.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

	@Query("select v from Vehicle v where v.vehicleNumber= :vehicleNumber ")
	Vehicle getVehicleByNumber(@Param("vehicleNumber") String vehicleNumber);

}
