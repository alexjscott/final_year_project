package com.webapp.VehicleRental.service;

import com.webapp.VehicleRental.domain.Calendar;
import com.webapp.VehicleRental.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalenderService {

    @Autowired
    private CalendarRepository calenderRepository;

    public Object findAllCalendars(){
        return calenderRepository.findAll();
    }

    public Object findById(String i){
        return  calenderRepository.findById(i);
    }

    public void deleteById(String id){
        calenderRepository.deleteById(id);
    }

    public void save(Calendar b){
        calenderRepository.save(b);
    }
}
