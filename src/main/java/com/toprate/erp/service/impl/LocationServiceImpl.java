package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.model.Location;
import com.toprate.erp.repositories.LocationRepository;
import com.toprate.erp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;

    @Override
    public LocationDTO addLocation(LocationDTO locationDTO) {
        long id = locationRepository.insert(locationDTO.toModel());
        locationDTO.setLocationId(id);
        return locationDTO;
    }

    @Override
    public List<LocationDTO> findAll() {
        List<LocationDTO> locationDTOS = new ArrayList<>();
        List<Location> locations = locationRepository.findAll();
        if (locations != null && !locations.isEmpty()) {
            for (Location location : locations){
                locationDTOS.add(location.toDto());
            }
        }
        return  locationDTOS;
    }

    @Override
    public LocationDTO saveAndUpdate(LocationDTO locationDTO) {
        locationRepository.update(locationDTO.toModel());
        return locationDTO;
    }

    @Override
    public int deleteLocation(LocationDTO locationDTO) {
        long locationId = locationDTO.getLocationId();
        Location location = locationRepository.find(locationId);
        if (location!=null){
            locationRepository.delete(location);
            return 1;
        }
        return 0;
    }

    @Override
    public DataListDTO doSearch(LocationDTO obj) {
        List<LocationDTO> dtos = locationRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(dtos);
        data.setSize(obj.getPageSize());
        data.setTotal(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    public List<LocationDTO> getForAutocomplete(SearchDTO obj){
        return locationRepository.getForAutocomplete(obj);
    }
}
