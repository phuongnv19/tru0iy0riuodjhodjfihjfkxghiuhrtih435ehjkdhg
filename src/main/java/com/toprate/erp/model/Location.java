package com.toprate.erp.model;

import com.toprate.erp.dto.LocationDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOCATION")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "LOCATION_NAME")
    private String locationName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "OPEN_TIME")
    private Date openTime;

    @Column(name = "CLOSE_TIME")
    private Date closeTime;

    @Column(name = "LOCATION_CODE")
    private String locationCode;

    public LocationDTO toDto() {
        LocationDTO location = new LocationDTO();
        location.setLocationId(locationId);
        location.setLocationName(locationName);
        location.setAddress(address);
        location.setOpenTime(openTime);
        location.setCloseTime(closeTime);
        location.setLocationCode(locationCode);
        return location;
    }
}
