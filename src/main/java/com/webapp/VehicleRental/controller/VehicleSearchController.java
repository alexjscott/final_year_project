package com.webapp.VehicleRental.controller;

import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.exception.ResourceNotFoundException;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class VehicleSearchController extends HttpServlet {


    @Autowired
    private VehicleListingRepository vehicleListingRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * Pick the query to search database by city and country
     **/
    @GetMapping(value = "/vehicle/search_listings/recommend")
    public ResponseEntity<List<VehicleListing>> getRecommendedVehicles(HttpServletRequest req, HttpServletResponse res) {
        try {
            // get user from session
            HttpSession session = req.getSession();
            String userEmail = (String) session.getAttribute("email");
            int userid = Integer.parseInt(userRepository.finduseridbyemail(userEmail));
            User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userid));

            // Get users city and country
            String userCity = user.getCity();
            String userCountry = user.getCountry();
            List<VehicleListing> listings = null;
            listings = vehicleListingRepository.findAllByCity(userCity);
            listings.addAll(vehicleListingRepository.findAllByCountry(userCountry));

            return new ResponseEntity<>(listings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/vehicle/search_listings")
    public ResponseEntity<List<VehicleListing>> getVehicles(@RequestParam(value = "type", required = true) String type,
                                                          @RequestParam(value = "search", required = true) String search) {
        try {
            // Make string captilise first letter
            String pick = type.substring(0, 1).toUpperCase() + type.substring(1);

            /**
             * Pick the query to search database
             * This is very long-winded due to not being able to change the colum name in a native query
             **/
            List<VehicleListing> results = null;
            switch (pick) {
                case "City":
                    if (vehicleListingRepository.findAllByCity(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByCity(search);
                    break;
                case "Country":
                    if (vehicleListingRepository.findAllByCountry(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByCountry(search);
                    break;
                case "Price":
                    if (vehicleListingRepository.findAllByPrice(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByPrice(search);
                    break;
                case "Vehicle":
                    if (vehicleListingRepository.findAllByVehicle(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByVehicle(search);
                    break;
                case "Make":
                    if (vehicleListingRepository.findAllByMake(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByMake(search);
                    break;
                case "Model":
                    if (vehicleListingRepository.findAllByModel(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByModel(search);
                    break;
                case "Year":
                    if (vehicleListingRepository.findAllByYear(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByYear(search);
                    break;
                case "Doors":
                    if (vehicleListingRepository.findAllByDoors(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByDoors(search);
                    break;
                case "Colour":
                    if (vehicleListingRepository.findAllByColour(search) == null) {
                        throw new ResourceNotFoundException("No results found match the search criteria");
                    }
                    results = vehicleListingRepository.findAllByColour(search);
                    break;
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
