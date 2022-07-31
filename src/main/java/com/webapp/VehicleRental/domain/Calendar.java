package com.webapp.VehicleRental.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Implemenetation unfinished
 */

@Entity
public class Calendar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private VehicleListing vehicleListing;

//    @Column(name = "unavailable")
//    private List<String> unavailable;

}
