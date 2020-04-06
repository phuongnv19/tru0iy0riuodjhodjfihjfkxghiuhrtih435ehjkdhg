package com.toprate.erp.service;

import com.toprate.erp.dto.ActivityDTO;
import com.toprate.erp.model.Activity;

import java.util.List;

public interface ActivityService {

    ActivityDTO createEvent(ActivityDTO activityDTO) throws Exception;

    void delete(ActivityDTO activityDTO);

    void updateActivityEvent(ActivityDTO activityDTO);

    List<ActivityDTO> getListForUser();

    void insertToReactEvent(Long activityId,Long reactType) throws Exception;
}
