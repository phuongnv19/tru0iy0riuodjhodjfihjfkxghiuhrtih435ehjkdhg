package com.toprate.erp.rest;

import com.toprate.erp.dto.ActivityDTO;
import com.toprate.erp.dto.ReactEventDTO;
import com.toprate.erp.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ActivityRestController {
    @Autowired
    ActivityServiceImpl activityEventService;

    @PostMapping("/activity/create")
    public ResponseEntity create(@RequestBody ActivityDTO activityDTO) throws Exception {
        return new ResponseEntity(activityEventService.createEvent(activityDTO), HttpStatus.OK);
    }

    @PostMapping("/activity/delete")
    public ResponseEntity delete(@RequestBody ActivityDTO activityDTO) {
        activityEventService.delete(activityDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/activity/update")
    public ResponseEntity update(@RequestBody ActivityDTO activityDTO) {
        activityEventService.updateActivityEvent(activityDTO);
        return new ResponseEntity(activityDTO, HttpStatus.OK);
    }

    @GetMapping("/activity/loadActivity")
    public ResponseEntity loadActivity() {
        return new ResponseEntity(activityEventService.getListForUser(), HttpStatus.OK);
    }


    @PostMapping("/activity/reactEvent")
    public ResponseEntity reactEvent(@RequestBody ReactEventDTO reactEventDTO) throws Exception {
        activityEventService.insertToReactEvent(reactEventDTO.getEventId(),reactEventDTO.getReactType());
        return new ResponseEntity( HttpStatus.OK);
    }
}
