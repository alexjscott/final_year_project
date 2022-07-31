package com.webapp.VehicleRental.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.webapp.VehicleRental.bean.HashGenerator;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.exception.ResourceNotFoundException;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController extends HttpServlet {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService appUserService;

    // If login failed
    @RequestMapping(value="/user/loginAttempt")
    public String failed(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String failed = "";
        HttpSession session = req.getSession();
        failed = (String) session.getAttribute("loginFailed");
        return failed;
    }

    // Login
    @GetMapping("/user/validate/{email}/{password}")
    public RedirectView login(HttpServletRequest req,
                                    HttpServletResponse res,
                                    @PathVariable("email") String email,
                                    @PathVariable("password") String password) throws IOException {
        HttpSession session = req.getSession();

        // Create new id strings
        String userid1;String userid2;
        // Hash password
        password = HashGenerator.getSHA256(password);

        try {
            userid1 = userRepository.finduseridbyemail(email);
            userid2 = userRepository.finduseridbypassword(password);
        } catch (Exception e){
            System.out.println("/user/validate/{email}/{password} = Not found User");
            session.setAttribute("loginFailed", "loginFailed");
            return new RedirectView("/");
        }


        if (Objects.equals(userid1, userid2) && userid1 != null){
            // Set the users email in session
            session.setAttribute("email", email);
            session.setAttribute("loginFailed", "Success");
            return new RedirectView("/api/user/profile");
        }else{
            session.setAttribute("loginFailed", "loginFailed");
        }
        return new RedirectView("/");
    }

    // Search by email to get id
    @RequestMapping(value="/user/email/getid", method=GET)
    public String findByEmail(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        return userRepository.finduseridbyemail(email);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public RedirectView createUser(HttpServletRequest req, HttpServletResponse res, User user) throws IOException {
        try {
            User _user = userRepository.save(new User(user.getTitle(),
                    user.getName(),
                    user.getPhone(),
                    user.getCity(),
                    user.getCountry(),
                    user.getPostcode(),
                    user.getEmail(),
                    user.getPassword()));
            // set users session email
            HttpSession session = req.getSession();
            session.setAttribute("email", user.getEmail());
            new ResponseEntity<>(_user, HttpStatus.CREATED);
            return new RedirectView("/api/user/profile");
        }catch (Exception e){
            RedirectView redirectView = new RedirectView("/");
            redirectView.setStatusCode(HttpStatus.NO_CONTENT);
            return redirectView;
        }
    }
    @PutMapping("/user/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        _user.setTitle(user.getTitle());
        _user.setName(user.getName());
        _user.setPhone(user.getPhone());
        _user.setCity(user.getCity());
        _user.setCountry(user.getCountry());
        _user.setPostcode(user.getPostcode());
        _user.setEmail(user.getEmail());
        _user.setPassword(user.getPassword());
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
       if ( userRepository.existsById(id) ) {
           userRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    // Not used (For Testing and Admin use)
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
