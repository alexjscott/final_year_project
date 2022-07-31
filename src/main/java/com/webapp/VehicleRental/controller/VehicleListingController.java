package com.webapp.VehicleRental.controller;

import com.webapp.VehicleRental.domain.UploadFileResponse;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.exception.ResourceNotFoundException;
import com.webapp.VehicleRental.repository.FileRepository;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import com.webapp.VehicleRental.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class VehicleListingController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleListingRepository vehicleListingRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/user/{userId}/listings")
    public ResponseEntity<List<VehicleListing>> getAllListingsByUserId(@PathVariable(value = "userId") int userId) {
        try {
            if (!userRepository.existsById(userId)) {
                throw new ResourceNotFoundException("Not found User with id = " + userId);
            }
            List<VehicleListing> listings = vehicleListingRepository.findByUserId(userId);
            return new ResponseEntity<>(listings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listing/{id}")
    public ResponseEntity<VehicleListing> getListingsByUserId(@PathVariable(value = "id") String id) {
        try {
            VehicleListing listing = vehicleListingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Listing with id = " + id));
            return new ResponseEntity<>(listing, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Set clicked Session vehicle id
    @GetMapping("/setvehicleSession/{id}")
    public HttpStatus setSearchListing(HttpServletRequest req,
                                       HttpServletResponse res,
                                       @PathVariable(value = "id") String id) throws IOException {
        try {
            HttpSession session = req.getSession();
            session.setAttribute("vehicleid", id);
            return HttpStatus.OK;
        }catch (Exception e){
            System.out.println(e);
            return HttpStatus.BAD_REQUEST;
        }
    }

    // Get clicked Session vehicle id
    @GetMapping("/getvehicleSession")
    public ResponseEntity<String> getSearchListing(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            HttpSession session = req.getSession();
            String string = (String) session.getAttribute("vehicleid");
            return new ResponseEntity<>(string, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    VehicleListing filelisting;
    @PostMapping("/user/{userId}/listing")
    public RedirectView createListing(HttpServletRequest req,
                                      HttpServletResponse res,
                                      @PathVariable(value = "userId") int userId,
                                      VehicleListing listingRequest,
                                      MultipartFile[] files) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        try {
            VehicleListing vehicleListing = vehicleListingRepository.save(new VehicleListing(
                    listingRequest.getRegistration(),
                    listingRequest.getUploadFileResponse(),
                    listingRequest.getUser(),
                    listingRequest.getVehicletype(),
                    listingRequest.getDoors(),
                    listingRequest.getColour(),
                    listingRequest.getMake(),
                    listingRequest.getModel(),
                    listingRequest.getYear(),
                    listingRequest.getPrice(),
                    listingRequest.getInfo(),
                    listingRequest.getCity(),
                    listingRequest.getCountry(),
                    listingRequest.getMileage()));
            vehicleListing.setUser(user);
            filelisting = vehicleListing;
            uploadMultipleFiles(files);
            new ResponseEntity<>(vehicleListing, HttpStatus.CREATED);
            return new RedirectView("/api/user/profile");
        }catch (Exception e){
            System.out.println(e);
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new RedirectView("/api/listing/create");
        }
    }
    // File
    public ResponseEntity<UploadFileResponse> uploadFile(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        UploadFileResponse uploadFileResponse = fileRepository.save(new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize(), filelisting));

        return new ResponseEntity<>(uploadFileResponse, HttpStatus.CREATED);
    }


    public void uploadMultipleFiles(MultipartFile[] files) {
        Arrays.asList(files)
                .stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @PutMapping("/listing/edit/{reg}")
    public ResponseEntity<String> updateListing(@PathVariable("reg") String reg, VehicleListing listingRequest) {
        try {
            VehicleListing listing = vehicleListingRepository.findById(reg)
                    .orElseThrow(() -> new ResourceNotFoundException("ListingId " + reg + "not found"));

            //listing.setRegistration(listingRequest.getRegistration());
            //listing.setUploadFileResponse(listingRequest.getUploadFileResponse());
            listing.setVehicletype(listingRequest.getVehicletype());
            listing.setDoors(listingRequest.getDoors());
            listing.setColour(listingRequest.getColour());
            listing.setMake(listingRequest.getMake());
            listing.setModel(listingRequest.getModel());
            listing.setPrice(listingRequest.getPrice());
            listing.setInfo(listingRequest.getInfo());
            listing.setCity(listingRequest.getCity());
            listing.setCountry(listingRequest.getCountry());
            listing.setMileage(listingRequest.getMileage());
            new ResponseEntity<>(vehicleListingRepository.save(listing), HttpStatus.CREATED);
            return new ResponseEntity<>("Successful PUT Request", HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // using
    @DeleteMapping("/user/{userId}/listing/{reg}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteListing(@PathVariable(value = "userId") int userId,
                                                    @PathVariable("reg") String reg) {
        try {
            // Check User
            if (!userRepository.existsById(userId)) {
                throw new ResourceNotFoundException("Not found User with id : " + userId);
            }
            // Check Vehicle Registration
            if (!vehicleListingRepository.existsById(reg)) {
                throw new ResourceNotFoundException("Registration not found : " + reg);
            } else {
                vehicleListingRepository.deleteById(reg);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



//    @DeleteMapping("/users/{userId}/listings")
//    public ResponseEntity<List<VehicleListing>> deleteAllListingsOfUser(@PathVariable(value = "userId") int userId) {
//        if (!userRepository.existsById(userId)) {
//            throw new ResourceNotFoundException("Not found User with id = " + userId);
//        }
//        vehicleListingRepository.deleteByUserId(userId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
