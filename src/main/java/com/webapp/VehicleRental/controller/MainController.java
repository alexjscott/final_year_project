package com.webapp.VehicleRental.controller;

import com.webapp.VehicleRental.repository.VehicleListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    VehicleListingRepository vehicleListingRepository;

    // Landing page
    @RequestMapping("")
    public String home(){return "/index.html";}

    // Signup page
    @RequestMapping("/api/signup")
    public String signup(){
        return "/pages/user/signup/signup.html";
    }

    // Profile page
    @RequestMapping("/api/user/profile")
    public String profile(){
        return "/pages/profile/profile.html";
    }

    // Edit user page
    @RequestMapping("/api/edit/user")
    public String editUser(){
        return "/pages/user/edit/edit.html";
    }

    // Create listing page
    @RequestMapping("/api/listing/create")
    public String createListing(){
        return "/pages/listing/new/new.html";
    }

    // Edit listing page
    @RequestMapping("/api/listing/edit")
    public String editListing(){
        return "/pages/listing/edit/edit.html";
    }

    // Delete listing page
    @RequestMapping("/api/listing/delete")
    public String deleteListing(){
        return "/pages/listing/delete/delete.html";
    }

    // Search vehicles page
    @RequestMapping("/api/vehicle/search")
    public String searchListing(){
        return "/pages/Search/search.html";
    }

    // Show vehicle
    @RequestMapping("/api/show/vehiclelisting")
    public String showListing(){
        return "/pages/booking/show/clickedVehicle.html";
    }

    // showBookingDetails
    @RequestMapping("/api/booking/details")
    public String showBookingDetails(){
        return "/pages/booking/summary/summary.html";
    }

    // view bookings
    @RequestMapping("/api/bookings/hosted")
    public String viewHostedBookings(){
        return "/pages/booking/view/hosted.html";
    }

    // view bookings
    @RequestMapping("/api/bookings/reserved")
    public String viewReservedBookings(){
        return "/pages/booking/view/reserved.html";
    }

}
