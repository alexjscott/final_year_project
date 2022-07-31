package com.webapp.VehicleRental.domain;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_listing_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VehicleListing vehicleListing;

    @Column(name = "pickup_date")
    private String pickup_date;

    @Column(name = "drop_off_date")
    private String drop_off_date;

    @Column(name = "pickup_time")
    private String pickup_time;

    @Column(name = "days")
    private String days;

    @Column(name = "price")
    private String price;

    // constructor
    public Booking() {}

    public Booking(String pickup_date, String drop_off_date, String pickup_time, String days, String price) {
        this.pickup_date = pickup_date;
        this.drop_off_date = drop_off_date;
        this.pickup_time = pickup_time;
        this.days = days;
        this.price = price;
    }

    // getters and setters

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public VehicleListing getVehicleListing() {
        return vehicleListing;
    }

    public void setVehicleListing(VehicleListing vehicleListing) {
        this.vehicleListing = vehicleListing;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getDrop_off_date() {
        return drop_off_date;
    }

    public void setDrop_off_date(String drop_off_date) {
        this.drop_off_date = drop_off_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPrice() {return price;}

    public void setPrice(String price) {this.price = price;}
}