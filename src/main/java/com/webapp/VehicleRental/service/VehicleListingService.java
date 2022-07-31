package com.webapp.VehicleRental.service;

import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleListingService {
    @Autowired
    private VehicleListingRepository vehicleListingRepository;

    public Object findAllVehicles(){
        return vehicleListingRepository.findAll();
    }

    public Object findById(String i){
        return  vehicleListingRepository.findById(i);
    }

    public void deleteById(String id){
        vehicleListingRepository.deleteById(id);
    }

    public void save(VehicleListing v){
        vehicleListingRepository.save(v);
    }
}
