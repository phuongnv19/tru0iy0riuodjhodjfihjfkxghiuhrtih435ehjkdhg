package com.toprate.erp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "react_event")
@Data
public class ReactEvent {
    @Id
    @SequenceGenerator(name = "pk_sequence_react_event", sequenceName = "REACT_EVENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_react_event")
    @Column(name = "REACT_ID")
    private Long reactId;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "REACT_TYPE")
    private int reactType;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "CREATED_TIME")
    private Date createdTime;
}
