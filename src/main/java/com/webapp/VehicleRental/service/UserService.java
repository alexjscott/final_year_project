package com.webapp.VehicleRental.service;

import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object findAllUsers(){
        return userRepository.findAll();
    }

    public Object findById(int i){
        return  userRepository.findById(i);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public void save(User u){
        userRepository.save(u);
    }


}
