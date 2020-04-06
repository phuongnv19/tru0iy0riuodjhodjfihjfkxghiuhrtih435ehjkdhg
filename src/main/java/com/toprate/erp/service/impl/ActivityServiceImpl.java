package com.toprate.erp.service.impl;

import com.toprate.erp.dto.ActivityDTO;
import com.toprate.erp.dto.AttachDocumentDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.repositories.ActivityRepository;
import com.toprate.erp.repositories.AttachDocumentRepository;
import com.toprate.erp.service.ActivityService;
import com.toprate.erp.util.Constants;
import com.toprate.erp.util.MessageUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    AttachDocumentRepository attachDocumentRepository;

    @Autowired
    AuthenticationServiceImpl authenticationService;


    @Override
    @Transactional
    public ActivityDTO createEvent(ActivityDTO activityDTO) throws Exception {
        activityDTO.setActivityUserId(authenticationService.getCurrentUser().getUserId());
        activityDTO.setActivityCreatedTime(new Date());
        Long id= activityRepository.insert(activityDTO.toModel());
        activityDTO.setActivityId(id);
        if(activityDTO.getAttachDocumentDTOList().size()>0){
            for(AttachDocumentDTO documentDTO:activityDTO.getAttachDocumentDTOList()){
                documentDTO.setSourceId(id);
                documentDTO.setTableName(Constants.TABLE_NAME.ACTIVITY);
                attachDocumentRepository.insert(documentDTO.toModel());
            }
        }
        return activityDTO;
    }

    @Override
    @Transactional
    public void delete(ActivityDTO activityDTO) throws BusinessException {
        if(!authenticationService.getCurrentUser().getUserId().equals(activityDTO.getActivityUserId())){
            throw  new BusinessException(MessageUtils.getMessage("user.not.delete.permision"));
        }
            activityRepository.delete(activityDTO.toModel());
        attachDocumentRepository.deleteBySourceIdAndTableName(activityDTO.getActivityId(),Constants.TABLE_NAME.ACTIVITY);
    }

    @Override
    @Transactional
    public void updateActivityEvent(ActivityDTO activityDTO) throws BusinessException {
        activityRepository.update(activityDTO.toModel());
    if(!authenticationService.getCurrentUser().getUserId().equals(activityDTO.getActivityUserId())){
        throw  new BusinessException(MessageUtils.getMessage("user.not.edit.permision"));
    }
        if(activityDTO.getAttachDocumentDTOList().size()>0){
            attachDocumentRepository.deleteBySourceIdAndTableName(activityDTO.getActivityId(),Constants.TABLE_NAME.ACTIVITY);

            for(AttachDocumentDTO documentDTO:activityDTO.getAttachDocumentDTOList()){
                documentDTO.setSourceId(activityDTO.getActivityId());
                documentDTO.setTableName(Constants.TABLE_NAME.ACTIVITY);
                attachDocumentRepository.insert(documentDTO.toModel());
            }
        }

    }

    @Override
    public List<ActivityDTO> getListForUser() {
        List<ActivityDTO> listExport = Lists.newArrayList();
        SysUserDTO  userDTO= authenticationService.getCurrentUser();
        List<ActivityDTO> listQuery=   activityRepository.getListActivityByUser(userDTO.getUserId());

        ActivityDTO ab = new ActivityDTO();
        for (Iterator<ActivityDTO> interator = listQuery.iterator(); interator.hasNext();) {
            ActivityDTO wi = interator.next();

            if (ab.getActivityId() == null) {
                ab = new ActivityDTO();
                ab.setActivityId(wi.getActivityId());
                ab.setActivityHeading(wi.getActivityHeading());
                ab.setActivityContent(wi.getActivityContent() );
                ab.setActivityEmbededLink(wi.getActivityEmbededLink() );
                ab.setActivityIsPinned(wi.getActivityIsPinned() );
                ab.setActivityType(wi.getActivityType() );
                ab.setActivityUserId(wi.getActivityUserId());
                ab.setActivityCreatedTime(wi.getActivityCreatedTime() );
                ab.setEventFromTime(wi.getEventFromTime() );
                ab.setEventToTime(wi.getEventToTime());
                ab.setListTagLocation(wi.getListTagLocation() );
                ab.setListTagUser(wi.getListTagUser() );
                ab.setLocationDescription(wi.getLocationDescription());
                ab.setIsDeleteEdit(wi.getIsDeleteEdit());
                ab.setIsReact(wi.getIsReact());
                ab.setCountPersionJoin(wi.getCountPersionJoin());
                ab.setCountPersionNotJoin(wi.getCountPersionNotJoin());
                listExport.add(ab);
            }
            if (ab.getActivityId().compareTo(wi.getActivityId()) != 0) {
                ab = new ActivityDTO();
                ab.setActivityId(wi.getActivityId());
                ab.setActivityHeading(wi.getActivityHeading());
                ab.setActivityContent(wi.getActivityContent() );
                ab.setActivityEmbededLink(wi.getActivityEmbededLink() );
                ab.setActivityIsPinned(wi.getActivityIsPinned() );
                ab.setActivityType(wi.getActivityType() );
                ab.setActivityUserId(wi.getActivityUserId());
                ab.setActivityCreatedTime(wi.getActivityCreatedTime() );
                ab.setEventFromTime(wi.getEventFromTime() );
                ab.setEventToTime(wi.getEventToTime());
                ab.setListTagLocation(wi.getListTagLocation() );
                ab.setListTagUser(wi.getListTagUser() );
                ab.setLocationDescription(wi.getLocationDescription());
                ab.setIsDeleteEdit(wi.getIsDeleteEdit());
                ab.setIsReact(wi.getIsReact());
                ab.setCountPersionJoin(wi.getCountPersionJoin());
                ab.setCountPersionNotJoin(wi.getCountPersionNotJoin());
                listExport.add(ab);
            }

            if (ab.getActivityId().compareTo(wi.getActivityId()) == 0 && null!= wi.getDocumentPath()) {
                AttachDocumentDTO Ana = new AttachDocumentDTO();
                Ana.setSourceId(wi.getActivityId());
                Ana.setDocumentName(wi.getDocumentName());
                Ana.setDocumentPath(wi.getDocumentPath() );
                ab.getAttachDocumentDTOList().add(Ana);
            }
        }

        return listExport;
    }

    @Override
    public void insertToReactEvent(Long activityId,Long reactType) throws Exception  {
        activityRepository.insertToReactEvent(authenticationService.getCurrentUser().getUserId(),activityId,reactType);
    }


}
