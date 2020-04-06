package com.toprate.erp.rest;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class LocationRestController {

    @Autowired
    LocationServiceImpl locationService;

    @PostMapping("/location/create")
    public ResponseEntity<?> create(@RequestBody LocationDTO locationDTO){
        return new ResponseEntity(
                locationService.addLocation(locationDTO), HttpStatus.CREATED);
    }

    @PostMapping("/location/update")
    public ResponseEntity<?> updateLocation(@RequestBody LocationDTO locationDTO){
        return new ResponseEntity(locationService.saveAndUpdate(locationDTO),HttpStatus.OK);
    }

    @PostMapping("/location/delete")
    public ResponseEntity<?> deleteLocation(@RequestBody LocationDTO locationDTO){
        if (locationService.deleteLocation(locationDTO) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/location/doSearch")
    public ResponseEntity doSearch(@RequestBody LocationDTO locationDTO){
        return new ResponseEntity(locationService.doSearch(locationDTO), HttpStatus.OK);
    }

}
