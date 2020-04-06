package com.toprate.erp.rest;

import com.toprate.erp.dto.CrmBookingDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.model.Member;
import com.toprate.erp.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookingRestController extends AbstractRestController {

    @Autowired
    private Environment environment;

    @Autowired
    private ProductServiceImpl productServiceImpl;


    // lam giong do search product
    @PostMapping("/booking/doSearchProduct")
    public ResponseEntity doSearch(@Valid @RequestBody CrmBookingDTO obj) {
        return new ResponseEntity<>(productServiceImpl.doSearchForBooking(obj), HttpStatus.OK);
    }


}
