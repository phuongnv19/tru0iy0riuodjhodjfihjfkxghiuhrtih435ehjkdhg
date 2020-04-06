package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.LocationDTO;

import java.util.List;

public interface LocationService {

    LocationDTO addLocation(LocationDTO locationDTO);

    List<LocationDTO> findAll();

    LocationDTO saveAndUpdate(LocationDTO locationDTO);

    int deleteLocation(LocationDTO locationDTO);

    DataListDTO doSearch(LocationDTO locationDTO);
}
