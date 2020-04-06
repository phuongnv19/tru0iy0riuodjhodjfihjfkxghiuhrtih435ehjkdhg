package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.CrmBookingDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.dto.ObjFailsDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Location;
import com.toprate.erp.model.Product;
import com.toprate.erp.model.ProductCategory;
import com.toprate.erp.repositories.LocationRepository;
import com.toprate.erp.repositories.ProductCategoryRepository;
import com.toprate.erp.repositories.ProductRepository;
import com.toprate.erp.service.ProductService;
import com.toprate.erp.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    protected static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    private Environment environment;

    StringBuffer errorDetail = new StringBuffer();

    List<ProductDTO> lstError;

    @Override
    public DataListDTO doSearch(ProductDTO obj) {
        List<ProductDTO> ls = productRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public DataListDTO doSearchForMasterCalender(ProductDTO obj) {
        List<ProductDTO> ls = productRepository.doSearchForMasterCalender(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public ProductDTO getDetailForMasterCalender(Long product_id) {
        Product product = productRepository.findByFiled("productId", product_id);

        if (product == null) {
            throw new BusinessException(MessageUtils.getMessage("product.not.exist"));
        }
        return product.toDto();
    }


    @Override
    public ProductDTO insert(ProductDTO obj) throws Exception {
        if (!checkCode(obj.getProductCode(), null)) {
            throw new BusinessException(MessageUtils.getMessage("duplicate.product"));
        }
        obj.setCreateDate(new Date());
//        obj.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId());
        Long id = productRepository.insert(obj.toModel());
        obj.setProductId(id);
        return obj;
    }

    @Override
    public ProductDTO update(ProductDTO obj) throws Exception {
        if (!checkCode(obj.getProductCode(), obj.getProductId())) {
            throw new BusinessException(MessageUtils.getMessage("duplicate.product"));
        }
        productRepository.update(obj.toModel());
        return obj;
    }

    private Boolean checkCode(String productCode, Long appParamId) {
        Product obj = productRepository.findByFiled("productCode", productCode);

        if (appParamId == null) {
            if (obj == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (obj == null) {
                return true;
            } else if (obj != null && obj.getProductId() == appParamId) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> autoCompleteSearch(SearchDTO obj) {

        return productRepository.autoCompleteSearch(obj);
    }

    @Override
    public ProductDTO importSaleInventory(String fileInput) throws Exception {
        String lstheader = ConfigResourceUtils.getContent("list_header_import");
        String headerNumber = ConfigResourceUtils.getContent("header_number_of_product");
        String[] arrHeader = lstheader.split(",");
        // lay danh sach header template vao map
        HashMap<Integer, String> mapHeader = new HashMap<>();
        for (int i = 0; i < arrHeader.length; i++) {
            mapHeader.put(i, arrHeader[i]);
        }
        lstError = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        Long countSuccess = 0L;
        List<ProductDTO> workLst = new ArrayList<>();
        List<ObjFailsDTO> workLstErr = new ArrayList<>();
        try {

            FileInputStream excelFile = new FileInputStream(new File(fileInput));

            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();
            int count = 0;


            for (Row row : sheet) {
                count++;
                boolean isRowEmpty = ExcelUtils.checkIfRowIsEmpty(row);
                if (isRowEmpty) {
                    continue;
                }
                // validate header template
                if (count == 4) {
                    for (int i = 0; i < Integer.parseInt(headerNumber); i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        boolean checkHeader = CommonUtils.validateHeaderTemplateImport(mapHeader.get(i), formatter.formatCellValue(cell));
                        if (!checkHeader) {
                            //Co can thong bao vi tri sai khong nhi???
                            throw new BusinessException("Template import sản phẩm không đúng!");
                        }
                    }
                }

                if (count >= 5) {

//                    boolean checkColumn1 = true;
//                    boolean checkColumn2 = true;
//                    boolean checkColumn3 = true;
//                    boolean checkColumn4 = true;
//                    boolean checkColumn5 = true;
//                    boolean checkColumn6 = true;
//                    boolean checkColumn8 = true;
//                    boolean checkColumn9 = true;
//                    boolean checkColumn10 = true;
//                    boolean checkColumn11 = true;
//                    boolean checkColumn12 = true;
//                    boolean checkColumn14 = true;
//                    boolean checkColumn15 = true;
//                    boolean checkColumn16 = true;
//                    boolean checkColumn17 = true;
                    boolean checkAll = true;
                    String productName = "";
                    String location = "";
                    String productParent = "";
                    String productCategory = "";
                    String productType = "";
                    String productCode = "";
                    String description = "";
                    String rate = "";
                    String quantity = "";
                    String unit = "";
                    String workstation = "";
                    String value = "";
                    String capacity = "";
                    String monthlyfee = "";
                    String weeklyfee = "";
                    String hourlyfee = "";
                    String bonusHours = "";
                    String bonusCredit = "";
                    errorDetail = new StringBuffer();
                    for (int i = 0; i < Integer.parseInt(headerNumber); i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // Check format file exel

                        if (cell.getColumnIndex() == 1) {
                            productName = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(productName, 1, checkAll);
                        }
                        if (cell.getColumnIndex() == 3) {
                            location = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(location, 3, checkAll);
                        }
                        if (cell.getColumnIndex() == 2) {
                            productParent = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(productParent, 2, checkAll);
                        }
                        if (cell.getColumnIndex() == 4) {
                            productCategory = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(productCategory, 4, checkAll);
                        }
                        if (cell.getColumnIndex() == 5) {
                            productType = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(productType, 5, checkAll);
                        }
                        if (cell.getColumnIndex() == 6) {
                            productCode = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(productCode, 6, checkAll);
                        }
                        if (cell.getColumnIndex() == 7) {
                            description = formatter.formatCellValue(cell);
                        }
                        if (cell.getColumnIndex() == 8) {
                            rate = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(rate, 8, checkAll);
                        }
                        if (cell.getColumnIndex() == 9) {
                            quantity = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(quantity, 9, checkAll);
                        }
                        if (cell.getColumnIndex() == 10) {
                            unit = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(unit, 10, checkAll);
                        }
                        if (cell.getColumnIndex() == 11) {
                            workstation = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(workstation, 11, checkAll);
                        }
                        if (cell.getColumnIndex() == 12) {
                            value = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(value, 12, checkAll);
                        }
                        if (cell.getColumnIndex() == 13) {
                            capacity = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(capacity, 13, checkAll);
                        }
                        if (cell.getColumnIndex() == 14) {
                            monthlyfee = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(monthlyfee, 14, checkAll);
                        }
                        if (cell.getColumnIndex() == 15) {
                            weeklyfee = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(weeklyfee, 15, checkAll);
                        }
                        if (cell.getColumnIndex() == 16) {
                            hourlyfee = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(hourlyfee, 16, checkAll);
                        }
                        if (cell.getColumnIndex() == 17) {
                            bonusHours = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(bonusHours, 17, checkAll);
                        }
                        if (cell.getColumnIndex() == 18) {
                            bonusCredit = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(bonusCredit, 17, checkAll);
                        }
                    }

                    if (checkAll) {
                        //them vao danh sach ban ghi import thanh cong
                        ProductDTO newObj = new ProductDTO();
                        Product ob = productRepository.findByFiled("productCode", productCode);
                        newObj.setProductName(productName);
                        newObj.setProductLocation(productParent);
                        newObj.setParentProductCode(location);
                        newObj.setProductCategoryId(productCategory);
                        newObj.setProductType(productType);
                        newObj.setProductCode(productCode);
                        newObj.setProductDescription(description);
                        newObj.setProductRate(Double.parseDouble(rate));
                        newObj.setProductQuantity(Double.parseDouble(quantity));
                        newObj.setProductUnit(Double.parseDouble(unit));
                        newObj.setProductWorkstation(Long.parseLong(workstation));
                        newObj.setProductValue(Double.parseDouble(value));
                        newObj.setProductCapacity(Long.parseLong(capacity));
                        newObj.setProductMonthlyFee(Double.parseDouble(monthlyfee));
                        newObj.setProductWeeklyFee(Double.parseDouble(weeklyfee));
                        newObj.setProductHourlyFee(Double.parseDouble(hourlyfee));
                        newObj.setProductBonusHours(Long.parseLong(bonusHours));
                        newObj.setProductBonusCredit(Long.parseLong(bonusCredit));
                        newObj.setCreateDate(new Date());
                        newObj.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId());
                        if (ob != null) {
                            // Update bản ghi
                            newObj.setProductId(ob.getProductId());
                            productRepository.update(newObj.toModel());
                        } else {
                            // Thêm mới bản ghi
                            productRepository.insert(newObj.toModel());
                        }
                        countSuccess++;
                        workLst.add(newObj);
                    } else {
                        //them vao danh sach ban ghi import khong thanh cong
                        ObjFailsDTO failsDTO = new ObjFailsDTO();
                        failsDTO.setLabel1(productName);
                        failsDTO.setLabel2(productParent);
                        failsDTO.setLabel3(location);
                        failsDTO.setLabel4(productCategory);
                        failsDTO.setLabel5(productType);
                        failsDTO.setLabel6(productCode);
                        failsDTO.setLabel7(description);
                        failsDTO.setLabel8(rate);
                        failsDTO.setLabel9(quantity);
                        failsDTO.setLabel10(unit);
                        failsDTO.setLabel11(workstation);
                        failsDTO.setLabel12(value);
                        failsDTO.setLabel13(capacity);
                        failsDTO.setLabel14(monthlyfee);
                        failsDTO.setLabel15(weeklyfee);
                        failsDTO.setLabel16(hourlyfee);
                        failsDTO.setLabel17(bonusHours);
                        failsDTO.setLabel18(bonusCredit);
                        failsDTO.setErrDetails(errorDetail.toString());
                        workLstErr.add(failsDTO);
                    }

                }
            }

            workbook.close();

        } catch (NullPointerException pointerException) {
            // pointerException.printStackTrace();
            logger.error(pointerException.getMessage(), pointerException);
            throw new Exception(pointerException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new Exception(e.getMessage());
        }

        productDTO.setLstProductSuccess(workLst);
        productDTO.setWorkLstErr(workLstErr);
        productDTO.setCountFails(workLstErr.size());
        //thuc hien ghi file excel loi sau do xuat path ma hoa
        if (productDTO.getWorkLstErr().size() > 0) {
            String pathFails = exportExcelError(productDTO.getWorkLstErr());
            productDTO.setPathError(EncryptUtils.encryptFileUploadPath(pathFails));
        }
        productDTO.setWorkLstErr(new ArrayList<>());
        productDTO.setCountSuccess(countSuccess);


        return productDTO;
    }

    public boolean checkDataFromFileExel(String data, int columnIndex, boolean checkAll) {

        if (columnIndex == 1) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã sản phẩm quan đang để trống; ");
                return false;
            }
        }
        if (columnIndex == 2) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã vị trí đang để trống; ");
                return false;
            }
            Location locationDTO = locationRepository.findByFiled("locationCode", data);
            if (locationDTO == null) {
                errorDetail.append("Mã vị trí không tồn tại;");
                return false;
            }
        }
        if (columnIndex == 3) {
            if (StringUtils.isNotBlank(data)) {
                boolean checkParent = productRepository.checkProParentHasExist(data);
                if (!checkParent) {
                    errorDetail.append("Mã sản phẩm cha đã nhập không thuộc danh sách sản phẩm cha; ");
                    return false;
                }
            }
        }
        if (columnIndex == 4) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã mục sản phẩm đang để trống; ");
                return false;
            } else {
                ProductCategory productCategory = productCategoryRepository.findByFiled("productCategoryCode", data);
                if (productCategory == null) {
                    errorDetail.append("Mã mục sản phẩm không tồn tại; ");
                    return false;
                }
            }

        }
        if (columnIndex == 5) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã loại sản phẩm đang để trống; ");
                return false;
            } else {
                boolean checkMapping = productCategoryRepository.checkExistFtMappingCategoryAndType(data);
                if (!checkMapping) {
                    errorDetail.append("Mã loại sản phẩm không tồn tại hoặc chưa map với mục sản phẩm; ");
                    return false;
                }
            }
        }
        if (columnIndex == 6) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã sản phẩm đang để trống; ");
                return false;
            }
            if (data.length() > 20) {
                errorDetail.append("Mã sản phẩm không được lớn hơn 20 kí tự; ");
                return false;
            }
        }
        if (columnIndex == 8) {
            if (StringUtils.isNotBlank(data)) {
                boolean isDouble = ValidateUtils.isStringDouble(data);
                if (!isDouble) {
                    errorDetail.append("Giá đơn vị sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 9) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Số lượng sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 11) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Trạm đặt đang để trống; ");
                return false;
            } else {
                boolean isLong = ValidateUtils.isStringLong(data);
                if (!isLong) {
                    checkAll = false;
                    errorDetail.append("Trạm đặt không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 12) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Giá trị sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 13) {
            if (StringUtils.isNotBlank(data)) {
                boolean isLong = ValidateUtils.isStringLong(data);
                if (!isLong) {
                    errorDetail.append("Dung tích sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 14) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Phí tháng của sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 15) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Phí tuần của sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 16) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Phí giờ của sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 17) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringFloat(data);
                if (!isFloat) {
                    errorDetail.append("Giờ cộng thêm không đúng định dạng số; ");
                    return false;
                }
            }
        }

        if (columnIndex == 18) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringInt(data);
                if (!isFloat) {
                    errorDetail.append("Tín dụng ưu đãi không đúng định dạng số; ");
                    return false;
                }
            }
        }

        return checkAll;
    }


    public String exportExcelError(List<ObjFailsDTO> errorLst) throws Exception {
        // TODO Auto-generated method stub
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        String path = classLoader.getResource("template").getPath();
        InputStream file = new ClassPathResource(
                "template" + File.separatorChar + Constants.TEMPLATE_PRODUCT_IMPORT_FAILS).getInputStream();
//        InputStream file = new BufferedInputStream(new FileInputStream(path ));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);
        style.setWrapText(true);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        for (int i = 0; i < errorLst.size(); i++) {

            XSSFRow row1 = sheet.createRow(i + 4);

            XSSFCell cell = row1.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(i + 1);

            XSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(errorLst.get(i).getLabel1());

            XSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(errorLst.get(i).getLabel2());

            XSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(errorLst.get(i).getLabel3());

            XSSFCell cell4 = row1.createCell(4);
            cell4.setCellStyle(cellStyle);
            cell4.setCellValue(errorLst.get(i).getLabel4());

            XSSFCell cell5 = row1.createCell(5);
            cell5.setCellStyle(cellStyle);
            cell5.setCellValue(errorLst.get(i).getLabel5());

            XSSFCell cell6 = row1.createCell(6);
            cell6.setCellStyle(cellStyle);
            cell6.setCellValue(errorLst.get(i).getLabel6());

            XSSFCell cell7 = row1.createCell(7);
            cell7.setCellStyle(cellStyle);
            cell7.setCellValue(errorLst.get(i).getLabel7());

            XSSFCell cell8 = row1.createCell(8);
            cell8.setCellStyle(cellStyle);
            cell8.setCellValue(errorLst.get(i).getLabel8());

            XSSFCell cell9 = row1.createCell(9);
            cell9.setCellStyle(cellStyle);
            cell9.setCellValue(errorLst.get(i).getLabel9());

            XSSFCell cell10 = row1.createCell(10);
            cell10.setCellStyle(cellStyle);
            cell10.setCellValue(errorLst.get(i).getLabel10());

            XSSFCell cell11 = row1.createCell(11);
            cell11.setCellStyle(cellStyle);
            cell11.setCellValue(errorLst.get(i).getLabel11());

            XSSFCell cell12 = row1.createCell(12);
            cell12.setCellStyle(cellStyle);
            cell12.setCellValue(errorLst.get(i).getLabel12());

            XSSFCell cell13 = row1.createCell(13);
            cell13.setCellStyle(cellStyle);
            cell13.setCellValue(errorLst.get(i).getLabel13());

            XSSFCell cell14 = row1.createCell(14);
            cell14.setCellStyle(cellStyle);
            cell14.setCellValue(errorLst.get(i).getLabel14());

            XSSFCell cell15 = row1.createCell(15);
            cell15.setCellStyle(cellStyle);
            cell15.setCellValue(errorLst.get(i).getLabel15());

            XSSFCell cell16 = row1.createCell(16);
            cell16.setCellStyle(cellStyle);
            cell16.setCellValue(errorLst.get(i).getLabel16());

            XSSFCell cell17 = row1.createCell(17);
            cell17.setCellStyle(cellStyle);
            cell17.setCellValue(errorLst.get(i).getLabel17());

            XSSFCell cell18 = row1.createCell(18);
            cell18.setCellStyle(cellStyle);
            cell18.setCellValue(errorLst.get(i).getLabel18());

            XSSFCell cell119 = row1.createCell(19);
            cell119.setCellStyle(cellStyle);
            cell119.setCellValue(errorLst.get(i).getErrDetails());

        }
        file.close();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateTime = simpleDateFormat.format(date);
        String pathSave = environment.getRequiredProperty("spring.import_properties.folder_upload")+ File.separatorChar + Constants.FOLDER_SAVE_TEMPLATE_ERR + File.separatorChar + dateTime ;
        File file1 = new File(pathSave);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        File out = new File(pathSave + File.separatorChar + Constants.TEMPLATE_PRODUCT_IMPORT_FAILS);

        FileOutputStream outFile = new FileOutputStream(out);
        workbook.write(outFile);
        workbook.close();
        outFile.close();
        String pathResult = EncryptUtils.encryptFileUploadPath(Constants.TEMPLATE_PRODUCT_IMPORT_FAILS);
        return pathResult;
    }

    @Override
    public DataListDTO doSearchForBooking(CrmBookingDTO obj){
        List<CrmBookingDTO> ls = productRepository.doSearchForBooking(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

}
