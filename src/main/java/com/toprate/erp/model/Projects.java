package com.toprate.erp.model;

import com.toprate.erp.dto.ProjectDto;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * Projects.java
 */
@Entity
@Table(name = "PROJECTS")
@Data
public class Projects {
    @Id
    @SequenceGenerator(name="pk_sequence_project",sequenceName="PROJECTS_PROJECT_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_project")
    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "PROJECT_CODE")
    private String projectCode;

    @Column(name = "CREATOR_ID")
    private Long creatorId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "PROJECT_STATUS")
    private Long projectStatus;

    @Column(name = "PROJECT_DESCRIPTION")
    private String projectDescription;

    @Column(name = "CREATED_TIME")
    private Date createdTime;

    public ProjectDto toDto() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(projectId);
        projectDto.setProjectCode(projectCode);
        projectDto.setCreatorId(creatorId);
        projectDto.setProjectName(projectName);
        projectDto.setProjectStatus(projectStatus);
        projectDto.setProjectDescription(projectDescription);
        projectDto.setCreatedTime(createdTime);
        return projectDto;
    }
}
