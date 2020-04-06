package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Projects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * ProjectDto.java
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDto extends ErpBaseDTO {
    private Long projectId;

    @AdjHistory(field = "PROJECT_CODE")
    private String projectCode;

    @AdjHistory(field = "CREATOR_ID")
    private Long creatorId;

    @AdjHistory(field = "PROJECT_NAME")
    private String projectName;

    @AdjHistory(field = "PROJECT_STATUS")
    private Long projectStatus;

    @AdjHistory(field = "PROJECT_DESCRIPTION")
    private String projectDescription;


    @AdjHistory(field = "CREATED_TIME")
    private Date createdTime;

    public Projects toModel() {
        Projects projects = new Projects();
        projects.setProjectId(projectId);
        projects.setProjectCode(projectCode);
        projects.setCreatorId(creatorId);
        projects.setProjectName(projectName);
        projects.setProjectStatus(projectStatus);
        projects.setProjectDescription(projectDescription);
        projects.setCreatedTime(createdTime);
        return projects;
    }
}
