package com.webapp.VehicleRental;

import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.property.FileStorageProperties;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class VehicleRentalApplication implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleListingRepository vehicleListingRepository;

	public static void main(String[] args) {
		SpringApplication.run(VehicleRentalApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/**
		Test data has been added by the import.sql file in the resources folder. Delete file to remove all test data from the app.
		 I chose to keep it up, to allow the features such as 'search' to be demonstrated.
		 Obviously it would have no vehicles to search in the apps initial state.
		**/

		// Add user for testing
//		User u1 = new User();
//		u1.setTitle("Mr");
//		u1.setName("Joe Bloggs");
//		u1.setPhone("0116 252 2522");
//		u1.setCity("Leicester");
//		u1.setCountry("England");
//		u1.setPostcode("LE1 7RH");
//		u1.setEmail("email@gmail.com");
//		u1.setPassword("password");
//		userRepository.save(u1);

		// Add a listing for testing
//		VehicleListing v1 = new VehicleListing();
//		v1.setRegistration("BN07BUW");
//		v1.setUser(u1);
//		v1.setVehicletype("Car");
//		v1.setDoors(3);
//		v1.setColour("Black");
//		v1.setMake("BMW");
//		v1.setModel("3 Series");
//		v1.setPrice(100);
//		v1.setYear("2007");
//		v1.setInfo("Looks like new");
//		v1.setCity("Leicester");
//		v1.setCountry("England");
//		vehicleListingRepository.save(v1);
//
//		// Add a listing for testing
//		Listing l2 = new Listing();
//		l2.setColour("Blue");
//		l2.setRegistration("**** ***");
//		l2.setMake("VW");
//		l2.setModel("POLO");
//		l2.setPrice(1000);
//		l2.setDate("Today");
//		l2.setInformation("Few scratches");
//		l2.setDate_available("Today");
//		l2.setDate_unavailable("None");
//		l2.setLocation("Leicester");
//		listrepo.save(l2);
//
//		// Add a listing for testing
//		Listing l3 = new Listing();
//		l3.setColour("Gray");
//		l3.setRegistration("**** ***");
//		l3.setMake("VW");
//		l3.setModel("GOLF");
//		l3.setPrice(3000);
//		l3.setDate("Today");
//		l3.setInformation("Brand new");
//		l3.setDate_available("Today");
//		l3.setDate_unavailable("None");
//		l3.setLocation("Manchester");
//		listrepo.save(l3);

	}
}
