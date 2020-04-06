package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.hibernate.type.LongType;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
    private Long activityId;

    @AdjHistory(field = "activity_heading")
    private String activityHeading;

    @AdjHistory(field = "activity_content")
    private String activityContent;

    @AdjHistory(field = "activity_embeded_link")
    private String activityEmbededLink;

    @AdjHistory(field = "activity_type")
    private Long activityType;

    @AdjHistory(field = "activity_is_pinned")
    private Long activityIsPinned;

    @AdjHistory(field = "activity_user_id")
    private Long activityUserId;

    @AdjHistory(field = "activity_created_time")
    private Date activityCreatedTime;

    @AdjHistory(field = "EVENT_FROM_TIME")
    private Date eventFromTime;

    @AdjHistory(field = "EVENT_TO_TIME")
    private Date eventToTime;

    @AdjHistory(field = "LOCATION_DESCRIPTION")
    private String locationDescription;

    @AdjHistory(field = "list_tag_user")
    private String listTagUser;

    @AdjHistory(field = "list_tag_location")
    private String listTagLocation;


    private List<AttachDocumentDTO> attachDocumentDTOList= Lists.newArrayList();

    private String documentName;
    private String documentPath;
    private Boolean isReact;
    private Boolean isDeleteEdit;
    private Long countPersionJoin;
    private  Long countPersionNotJoin;


    public Activity toModel() {

        Activity activityEvent = new Activity();
        activityEvent.setActivityId(activityId);
        activityEvent.setActivityHeading(activityHeading);
        activityEvent.setActivityContent(activityContent);
        activityEvent.setActivityEmbededLink(activityEmbededLink);
        activityEvent.setActivityIsPinned(activityIsPinned);
        activityEvent.setActivityUserId(activityUserId);
        activityEvent.setActivityCreatedTime(activityCreatedTime);
        activityEvent.setActivityType(activityType);
        activityEvent.setEventFromTime(eventFromTime);
        activityEvent.setEventToTime(eventToTime);
        activityEvent.setLocationDescription(locationDescription);
        activityEvent.setListTagLocation(listTagLocation);
        activityEvent.setListTagUser(listTagUser);


        return activityEvent;

    }
}
