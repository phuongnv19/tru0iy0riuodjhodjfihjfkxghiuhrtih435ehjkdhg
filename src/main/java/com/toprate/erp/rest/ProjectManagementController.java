package com.toprate.erp.rest;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ProjectDto;
import com.toprate.erp.dto.TaskAssigneesDto;
import com.toprate.erp.dto.TaskCommentDto;
import com.toprate.erp.dto.TaskDto;
import com.toprate.erp.service.impl.ProjectManagementServiceImpl;
import com.toprate.erp.service.impl.TaskTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * To change this template use File | Settings | File and Code Templates.
 */
@RestController
public class ProjectManagementController extends AbstractRestController {
    @Autowired
    private ProjectManagementServiceImpl projectManagementService;

    @Autowired
    private TaskTypeServiceImpl taskTypeService;

    @PostMapping("/projectManagement/saveTask")
    public ResponseEntity saveTask(@Valid @RequestBody TaskDto taskDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.insertTask(taskDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/updateTask")
    public ResponseEntity updateTask(@Valid @RequestBody TaskDto taskDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.updateTask(taskDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/deleteTask")
    public ResponseEntity delete(@RequestBody TaskDto taskDto) {
        if (projectManagementService.deleteTask(taskDto)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projectManagement/saveProject")
    public ResponseEntity saveProject
            (@Valid @RequestBody ProjectDto projectDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.insertProject(projectDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/updateProject")
    public ResponseEntity updateProject(@Valid @RequestBody ProjectDto projectDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.updateProject(projectDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/deleteProject")
    public ResponseEntity deleteProject(@RequestBody ProjectDto projectDto) {
        if (projectManagementService.deleteProject(projectDto)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projectManagement/commentTask")
    public ResponseEntity commentTask(@Valid @RequestBody TaskCommentDto taskCommentDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.insertCommentTask(taskCommentDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/saveSubTask")
    public ResponseEntity saveSubTask(@Valid @RequestBody TaskAssigneesDto taskAssigneesDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.insertSubTask(taskAssigneesDto), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/updateSubTask")
    public ResponseEntity updateSubTask(@Valid @RequestBody TaskAssigneesDto taskAssigneesDto) throws Exception {
        return new ResponseEntity<>(projectManagementService.updateSubTask(taskAssigneesDto), HttpStatus.OK);
    }

    @GetMapping("/projectManagement/getTask")
    public ResponseEntity findAll() {
        return new ResponseEntity(projectManagementService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/getProject")
    public ResponseEntity findAllProject(@Valid @RequestBody SearchDTO obj) {
        return new ResponseEntity(projectManagementService.findAllProject(obj), HttpStatus.OK);
    }

    @PostMapping("/projectManagement/getTaskDetail")
    public ResponseEntity getTaskDetail(@RequestBody TaskDto taskDto) throws Exception {
        return new ResponseEntity(projectManagementService.getTaskDetail(taskDto), HttpStatus.OK);
    }

    @GetMapping("/projectManagement/getTypeTask")
    public ResponseEntity getTypeTask() {
        return new ResponseEntity(taskTypeService.findAll(), HttpStatus.OK);
    }
}
