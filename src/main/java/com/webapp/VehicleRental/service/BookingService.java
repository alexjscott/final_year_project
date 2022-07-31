package com.webapp.VehicleRental.service;

import com.webapp.VehicleRental.domain.Booking;
import com.webapp.VehicleRental.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Object findAllBookings(){
        return bookingRepository.findAll();
    }

    public Object findById(int i){
        return  bookingRepository.findById(i);
    }

    public void deleteById(int id){
        bookingRepository.deleteById(id);
    }

    public void save(Booking b){
        bookingRepository.save(b);
    }

}
