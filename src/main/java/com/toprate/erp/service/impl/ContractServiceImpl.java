package com.toprate.erp.service.impl;


import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ResultDTO;
import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.dto.MemberDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.*;
import com.toprate.erp.repositories.*;
import com.toprate.erp.service.ContractService;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractItemRepository contractItemRepository;

    @Autowired
    private InventoryCalendarRepository inventoryCalendarRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String CATE_PK = "PK";
    private static final String CATE_CD = "CD";
    private static final String CATE_VF = "VF";
    private static final String LOCAL = "local";
    private static final String MEMBER = "member";
    private static final String USERS = "users";
    private static final Long LOCKED_CONTRACT = (long) 2;
    private static final Long UNLOCKED_CONTRACT = (long) 1;
    private static final Long DEFAULT_LOCKED_CONTRACT = (long) 0;
    private static final Long ISRECURRING_OFFICE = (long) 1;
    private static final Long ISRECURRING_ADD_ON = (long) 0;

    /*
     * api create contract
     * */
    @Override
    public ContractDTO insert(HttpServletRequest request, ContractDTO contractDto) throws Exception {
        try {
            //get info user_id tu token
            SysUserDTO users = this.getUserInfor(request);
            Location location = locationRepository.findByFiled("locationId", contractDto.getLocationId());
            Member member = memberRepository.getParentMemberId(contractDto.getCompanyId());

            this.checkIsNotNull(users, member, location);

            contractDto.setCreatedBy(users.getUserId());
            //get parentMemberId khi co companyId trong table member
            contractDto.setParentMemberId(member.getParentMemberId());
            // Thoi gian tao hop dong
            contractDto.setCreateTime(new Date());
            //default lockStatus = 0;
            contractDto.setLockStatus(DEFAULT_LOCKED_CONTRACT);
            Long contractId = contractRepository.insert(contractDto.toModel());
            contractDto.setId(contractId);
            //handle list office
            this.insertOffice(contractDto, users);
            //handle list add-on
            this.insertAddOn(contractDto, users);
        } catch (Exception e) {
            throw new BusinessException(MessageUtils.getMessage(e.getMessage()));
        }
        return contractDto;
    }

    /*
     * update contract
     * */
    @Override
    public ContractDTO update(HttpServletRequest request, ContractDTO contractDto) throws Exception {
        try {
            if (contractDto.getId() != null) {
                SysUserDTO user = this.getUserInfor(request);
                Location location = locationRepository.findByFiled("locationId", contractDto.getLocationId());
                Member member = memberRepository.getParentMemberId(contractDto.getCompanyId());

                this.checkIsNotNull(user, member, location);

                contractDto.setCreatedBy(user.getUserId());
                contractDto.setParentMemberId(member.getMemberId());
                contractDto.setParentMemberId(member.getParentMemberId());
                // Thoi gian thay doi hop dong
                contractDto.setModifiedTime(new Date());
                contractRepository.update(contractDto.toModel());
                List<ContractItem> listContract = contractItemRepository.find(
                        ContractItem.class, "contractId", contractDto.getId()
                );
                List<InventoryCalendar> listInventoryCalendar = inventoryCalendarRepository.find(
                        InventoryCalendar.class, "contractId", contractDto.getId()
                );
                if (!listContract.isEmpty()) {
                    listContract.forEach(ele -> {
                        contractItemRepository.delete(ele);
                    });
                }
                if (!listInventoryCalendar.isEmpty()) {
                    listInventoryCalendar.forEach(ele -> {
                        inventoryCalendarRepository.delete(ele);
                    });
                }
                //handle list office
                this.insertOffice(contractDto, user);
                //handle list add-on
                this.insertAddOn(contractDto, user);
            }
        } catch (Exception e) {
            throw new BusinessException(MessageUtils.getMessage(e.getMessage()));
        }
        return contractDto;
    }

    /*
     * Hien thi va tim kiem contract
     * */
    @Override
    public DataListDTO doSearch(ContractDTO obj) throws Exception {
        List<ContractDTO> ls = contractRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    /*
     * xem chi tiet 1 contract
     * */
    @Override
    public ContractDTO getDetailById(ContractDTO contractDTO) throws Exception {
        ContractDTO contract;
        try {
            Contract con = contractRepository.findByFiled("id", contractDTO.getId());
            if (con == null) {
                throw new BusinessException(MessageUtils.getMessage("getDetailById.contract.null"));
            }
            contract = con.toDto();
            Member member = memberRepository.findByFiled("memberId",contract.getParentMemberId());
            contract.setCompanyId(member.getCompanyId());
            if (contractDTO.getId() != null) {
                List<ContractItem> listContract = contractItemRepository.find(
                        ContractItem.class, "contractId", contractDTO.getId()
                );
                List<ContractItem> listOfficeContract =
                        listContract.stream()
                                .filter(ele -> ele.getIsRecurring() == 1)
                                .collect(Collectors.toList());
                contract.setListOfficeContract(listOfficeContract);

                List<ContractItem> listAddonContract =
                        listContract.stream()
                                .filter(ele -> ele.getIsRecurring() == 0)
                                .collect(Collectors.toList());
                contract.setListAddOnContract(listAddonContract);
            }
        } catch (Exception e) {
            throw new BusinessException(MessageUtils.getMessage(e.getMessage()));
        }
        return contract;
    }

    /*
     * insert table Inventory_Calendar
     * */
    private void insertInventoryCalendar(
            ContractItem ele,
            SysUserDTO users,
            Product product,
            ContractDTO contractDto
    ) {
        // handle insert table inventoryCalendar
        InventoryCalendar inventoryCalendar = new InventoryCalendar();
        inventoryCalendar.setCreateDate(new Date());
        inventoryCalendar.setContractId(contractDto.getId());
        inventoryCalendar.setProductId(ele.getProductId());
        inventoryCalendar.setCreateUserId(users.getUserId());
        inventoryCalendar.setParentProductId(product.getParentProductId());
        inventoryCalendar.setFromDate(contractDto.getPaidStartDate());
        inventoryCalendar.setToDate(contractDto.getPaidEndDate());
        inventoryCalendarRepository.insert(inventoryCalendar);
    }

    private void checkIsNotNull(SysUserDTO user, Member member, Location location) {
        if (user == null) {
            throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, USERS));
        }
        if (member == null) {
            throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, MEMBER));
        }
//        if (location == null) {
//            throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, LOCAL));
//        }
    }

    /*
     * insert office contractItem
     * */
    private void insertOffice(ContractDTO contractDto, SysUserDTO user) {
        String cate = this.getProductCategory(contractDto.getContractType());
        List<ContractItem> listOfficeContract = contractDto.getListOfficeContract();
        int numLockStatus = 0;
        // handle list office
        for (ContractItem ele : listOfficeContract) {
            Product product = productRepository.findByFiled("productId", ele.getProductId());
            Long contractPeriod = this.getContractPeriod(contractDto.getContractType());
            ele.setContractId(contractDto.getId());
            ele.setContractPeriod(contractPeriod);
            ele.setWeeklyFee(product.getProductWeeklyFee());
            ele.setMonthlyFee(product.getProductMonthlyFee());
            ele.setProductCategory(cate);
            ele.setBonusHours(product.getProductBonusHours());
            ele.setBonusCredit(product.getProductBonusCredit());
            ele.setIsRecurring(ISRECURRING_OFFICE);
            // tinh maxFree
            long maxFree = (long) Math.floor(((365 * 10000 / (7 * 12 * 5)) * ele.getLeaseTerm()) / 10000);
            if (maxFree > ele.getNumFreeWeeks()) {
                numLockStatus++;
            }
            ele.setBonusCredit(maxFree);
            contractItemRepository.insert(ele);
            // handle insert table inventoryCalendar
            this.insertInventoryCalendar(ele, user, product, contractDto);
        }
        // neu maxFree > 20 => lockstatus = 2
        if (numLockStatus > 0) {
            contractDto.setLockStatus(LOCKED_CONTRACT);
            contractRepository.update(contractDto.toModel());
        }
    }

    /*
     * insert add on
     * */
    private void insertAddOn(ContractDTO contractDto, SysUserDTO user) {
        List<ContractItem> listAddOnItem = contractDto.getListAddOnContract();
        for (ContractItem ele : listAddOnItem) {
            ele.setIsRecurring(ISRECURRING_ADD_ON);
            Product product = productRepository.findByFiled("productId", ele.getProductId());
            ele.setContractId(contractDto.getId());
            ele.setProductValue(product.getProductValue());
            ele.setProductDescription(product.getProductDescription());
            contractItemRepository.insert(ele);
            // handle insert table inventoryCalendar
            this.insertInventoryCalendar(ele, user, product, contractDto);
        }
    }

    private SysUserDTO getUserInfor(HttpServletRequest request) {
        SysUserDTO sysUser = (SysUserDTO) request.getAttribute("USER_INFO");
        return sysUser;
    }

    private String getProductCategory(long contractType) {
        String productCategory = null;
        switch ((int) contractType) {
            case 0:
                productCategory = CATE_PK;
                break;
            case 1:
                productCategory = CATE_CD;
                break;
            case 2:
                productCategory = CATE_VF;
                break;
        }
        return productCategory;
    }

    private Long getContractPeriod(long contractType) {
        switch ((int) contractType) {
            case 2:
                return (long) 1;
            case 1:
            case 0:
                return (long) 0;
            default:
                return null;
        }
    }
}

