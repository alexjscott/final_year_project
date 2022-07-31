package com.webapp.VehicleRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "vehicleListing")
public class VehicleListing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "registration", unique = true)
    private String registration;

    @JsonIgnore
    @OneToMany(cascade=ALL, mappedBy="vehicleListing")
    public Set<UploadFileResponse> uploadFileResponse;

    @ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "vehicletype")
    private String vehicletype;

    @Column(name = "doors")
    private int doors;

    @Column(name = "colour")
    private String colour;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private String year;

    @Column(name = "price")
    private float price;

    // Extra information about the vehicle.
    @Column(name = "info")
    private String info;

    // city and country
    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "mileage")
    private String mileage;

    // constructors
    public VehicleListing() {}

    public VehicleListing(String registration, UploadFileResponse uploadFileResponse, User user, String vehicletype, int doors, String colour, String make, String model, String year, float price, String info, String city, String country, String mileage) {
        this.registration = registration;
        this.uploadFileResponse = (Set<UploadFileResponse>) uploadFileResponse;
        this.user = user;
        this.vehicletype = vehicletype;
        this.doors = doors;
        this.colour = colour;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.info = info;
        this.city = city;
        this.country = country;
        this.mileage = mileage;
    }


    //... getters and setters
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public UploadFileResponse getUploadFileResponse() {
        return (UploadFileResponse) uploadFileResponse;
    }

    public void setUploadFileResponse(UploadFileResponse uploadFileResponse) {
        this.uploadFileResponse = (Set<UploadFileResponse>) uploadFileResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public String getMileage() {return mileage;}

    public void setMileage(String mileage) {this.mileage = mileage;}

}
