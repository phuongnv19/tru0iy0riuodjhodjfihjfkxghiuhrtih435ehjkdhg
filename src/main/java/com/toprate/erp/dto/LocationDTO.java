package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO extends ErpBaseDTO {
    private Long locationId;

    @AdjHistory(field = "LOCATION_NAME")
    private String locationName;

    @AdjHistory(field = "ADDRESS")
    private String address;

    @AdjHistory(field = "OPEN_TIME")
    private Date openTime;

    @AdjHistory(field = "CLOSE_TIME")
    private Date closeTime;

    @AdjHistory(field = "LOCATION_CODE")
    private String locationCode;

    public Location toModel (){
        Location location = new Location();
        location.setLocationId(locationId);
        location.setLocationName(locationName);
        location.setAddress(address);
        location.setOpenTime(openTime);
        location.setCloseTime(closeTime);
        location.setLocationCode(locationCode);
        return  location;
    }

}
