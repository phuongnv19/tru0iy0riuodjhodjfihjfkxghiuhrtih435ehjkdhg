package com.toprate.erp.model;

import lombok.Data;
import org.hibernate.type.LongType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_event")
@Data
public class Activity {
    @Id
    @SequenceGenerator(name = "pk_sequence_activity_event", sequenceName = "ACT_EVENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_activity_event")
    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "activity_heading")
    private String activityHeading;

    @Column(name = "activity_content")
    private String activityContent;

    @Column(name = "activity_embeded_link")
    private String activityEmbededLink;

    @Column(name = "activity_type")
    private Long activityType;

    @Column(name = "activity_is_pinned")
    private Long activityIsPinned;

    @Column(name = "activity_user_id")
    private Long activityUserId;

    @Column(name = "activity_created_time")
    private Date activityCreatedTime;

    @Column(name = "EVENT_FROM_TIME")
    private Date eventFromTime;

    @Column(name = "EVENT_TO_TIME")
    private Date eventToTime;

    @Column(name = "LOCATION_DESCRIPTION")
    private String locationDescription;

    @Column(name = "list_tag_user")
    private String listTagUser;

    @Column(name = "list_tag_location")
    private String listTagLocation;
}
