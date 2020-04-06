package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ObjFailsDTO;
import com.toprate.erp.dto.StockDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Location;
import com.toprate.erp.model.ProductCategory;
import com.toprate.erp.model.Stock;
import com.toprate.erp.repositories.LocationRepository;
import com.toprate.erp.repositories.ProductCategoryRepository;
import com.toprate.erp.repositories.StockRepository;
import com.toprate.erp.service.StockService;
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
public class StockServiceImpl implements StockService {
    protected static final Logger logger = Logger.getLogger(StockServiceImpl.class);
    @Autowired
    StockRepository stockRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private Environment environment;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    StringBuffer errorDetail = new StringBuffer();

    List<StockDTO> lstError;

    @Override
    public List<StockDTO> getListStock() {
        List<Stock> lstProduct = stockRepository.findAll();
        List<StockDTO> dtos = new ArrayList<>();
        if (lstProduct != null && !lstProduct.isEmpty()) {
            for (Stock stock : lstProduct) {
                dtos.add(stock.toDto());
            }
        }
        return dtos;
    }

    @Override
    public DataListDTO doSearch(StockDTO obj) {
        List<StockDTO> ls = stockRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public StockDTO insert(StockDTO obj) throws Exception {
        if (!checkCode(obj.getStockCode(), null)) {
            throw new BusinessException(MessageUtils.getMessage("duplicate.stock"));
        }
        obj.setCreateDate(new Date());
//        obj.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId());
        Long id = stockRepository.insert(obj.toModel());
        obj.setStockId(id);
        return obj;
    }

    @Override
    public StockDTO update(StockDTO obj) throws Exception {
        if (!checkCode(obj.getStockCode(), obj.getStockId())) {
            throw new BusinessException(MessageUtils.getMessage("duplicate.stock"));
        }
        stockRepository.update(obj.toModel());
        return obj;
    }

    private Boolean checkCode(String stockCode, Long appParamId) {
        Stock obj = stockRepository.findByFiled("stockCode", stockCode);

        if (appParamId == null) {
            if (obj == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (obj == null) {
                return true;
            } else if (obj != null && obj.getStockId().longValue() == appParamId) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public StockDTO importStockInventory(String fileInput) throws Exception {
        String lstheader = ConfigResourceUtils.getContent("list_header_import_stock");
        String headerNumber = ConfigResourceUtils.getContent("header_number_of_stock");
        String[] arrHeader = lstheader.split(",");
        // lay danh sach header template vao map
        HashMap<Integer, String> mapHeader = new HashMap<>();
        for (int i = 0; i < arrHeader.length; i++) {
            mapHeader.put(i, arrHeader[i]);
        }
        lstError = new ArrayList<>();
        StockDTO stockDTO = new StockDTO();
        Long countSuccess = 0L;
        List<StockDTO> workLst = new ArrayList<>();
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
                            throw new BusinessException("Template import kho không đúng!");
                        }
                    }
                }

                if (count >= 5) {

                    boolean checkAll = true;
                    String stockName = "";
                    String location = "";
                    String stockCategory = "";
                    String stockType = "";
                    String stockCode = "";
                    String description = "";
                    String initQuantity = "";
                    String quantity = "";
                    String unit = "";
                    String value = "";
                    errorDetail = new StringBuffer();
                    for (int i = 0; i < Integer.parseInt(headerNumber); i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // Check format file exel

                        if (cell.getColumnIndex() == 1) {
                            stockName = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(stockName, 1, checkAll);
                        }
                        if (cell.getColumnIndex() == 2) {
                            location = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(location, 2, checkAll);
                        }
                        if (cell.getColumnIndex() == 3) {
                            stockCategory = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(stockCategory, 3, checkAll);
                        }

                        if (cell.getColumnIndex() == 4) {
                            stockType = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(stockType, 4, checkAll);
                        }

                        if (cell.getColumnIndex() == 5) {
                            stockCode = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(stockCode, 5, checkAll);
                        }

                        if (cell.getColumnIndex() == 6) {
                            description = formatter.formatCellValue(cell);
                        }

                        if (cell.getColumnIndex() == 7) {
                            initQuantity = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(initQuantity, 7, checkAll);
                        }
                        if (cell.getColumnIndex() == 8) {
                            quantity = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(quantity, 8, checkAll);
                        }
                        if (cell.getColumnIndex() == 9) {
                            unit = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(unit, 9, checkAll);
                        }
                        if (cell.getColumnIndex() == 10) {
                            value = formatter.formatCellValue(cell);
                            checkAll = checkDataFromFileExel(value, 10, checkAll);
                        }

                    }

                    if (checkAll) {
                        //them vao danh sach ban ghi import thanh cong
                        StockDTO newObj = new StockDTO();
                        Stock ob = stockRepository.findByFiled("stockCode", stockCode);
                        newObj.setStockCode(stockCode);
                        newObj.setStockName(stockName);
                        newObj.setStockCategory(stockCategory);
                        newObj.setStockType(stockType);
                        newObj.setStockCode(stockCode);
                        newObj.setStockDescription(description);
                        newObj.setStockInitQuatity(Long.parseLong(initQuantity));
                        newObj.setStockQuantity(Long.parseLong(quantity));
                        newObj.setStockUnit(Long.parseLong(unit));
                        newObj.setStockValue(Double.parseDouble(value));
                        newObj.setCreateDate(new Date());
                        newObj.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId());
                        if (ob != null) {
                            // Update bản ghi
                            newObj.setStockId(ob.getStockId());
                            stockRepository.update(newObj.toModel());
                        } else {
                            // Thêm mới bản ghi
                            stockRepository.insert(newObj.toModel());
                        }
                        countSuccess++;
                        workLst.add(newObj);
                    } else {
                        //them vao danh sach ban ghi import khong thanh cong
                        ObjFailsDTO failsDTO = new ObjFailsDTO();
                        failsDTO.setLabel1(stockName);
                        failsDTO.setLabel2(location);
                        failsDTO.setLabel3(stockCategory);
                        failsDTO.setLabel4(stockType);
                        failsDTO.setLabel5(stockCode);
                        failsDTO.setLabel6(description);
                        failsDTO.setLabel7(initQuantity);
                        failsDTO.setLabel8(quantity);
                        failsDTO.setLabel9(unit);
                        failsDTO.setLabel10(value);
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

        stockDTO.setLstStockSuccess(workLst);
        stockDTO.setWorkLstErr(workLstErr);
        stockDTO.setCountFails(workLstErr.size());
        //thuc hien ghi file excel loi sau do xuat path ma hoa
        if (stockDTO.getWorkLstErr().size() > 0) {
            String pathFails = exportExcelError(stockDTO.getWorkLstErr());
            stockDTO.setPathError(EncryptUtils.encryptFileUploadPath(pathFails));
        }
        stockDTO.setWorkLstErr(new ArrayList<>());
        stockDTO.setCountSuccess(countSuccess);


        return stockDTO;
    }

    public boolean checkDataFromFileExel(String data, int columnIndex, boolean checkAll) {

        if (columnIndex == 1) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Tên kho(stock name) đang để trống; ");
                return false;
            }
        }
        if (columnIndex == 2) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã vị trí(location) đang để trống; ");
                return false;
            }
            Location locationDTO = locationRepository.findByFiled("locationCode", data);
            if (locationDTO == null) {
                errorDetail.append("Mã vị trí(location) không tồn tại;");
                return false;
            }
        }
        if (columnIndex == 3) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã danh mục kho(Stock Category (Code)) đang để trống; ");
                return false;
            }
            if (StringUtils.isNotBlank(data)) {
                // kiem tra ton tai trong db -- hien tai chua co DB
//                boolean checkParent = stockRepository.checkProParentHasExist(data);
//                if (!checkParent) {
//                    errorDetail.append("Mã danh mục kho không tồn tại; ");
//                    return false;
//                }
            }
        }
        if (columnIndex == 4) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Mã loại kho(Stock Type (Code)) đang để trống; ");
                return false;
            } else {
                 //kiem tra ton tai trong db và co mapping -- hien tai chua co DB
                ProductCategory productCategory = productCategoryRepository.findByFiled("productCategoryCode", data);
                if (productCategory == null) {
                    errorDetail.append("Stock Category (Code) không tồn tại; ");
                    return false;
                }
            }

        }
        if (columnIndex == 5) {
            if (StringUtils.isBlank(data)) {
                errorDetail.append("Stock Code đang để trống; ");
                return false;
            }
        }
        if (columnIndex == 7) {
            if (StringUtils.isNotBlank(data)) {
                boolean isFloat = ValidateUtils.isStringInt(data);
                if (!isFloat) {
                    errorDetail.append("Số lượng ban đầu(Init Quantity) không phải dạng số; ");
                    return false;
                }
            }
        }
        if (columnIndex == 8) {
            if (StringUtils.isNotBlank(data)) {
                boolean isDouble = ValidateUtils.isStringDouble(data);
                if (!isDouble) {
                    errorDetail.append("Số lượng(Quantity) không đúng định dạng số; ");
                    return false;
                }
            }
        }
        if (columnIndex == 9) {
            if (StringUtils.isNotBlank(data)) {
                boolean isLong = ValidateUtils.isStringLong(data);
                if (!isLong) {
                    errorDetail.append("Đơn vị(unit) sản phẩm không đúng định dạng số; ");
                    return false;
                }
            }
        }
        if (columnIndex == 10) {
            if (StringUtils.isNotBlank(data)) {
                boolean isDouble = ValidateUtils.isStringDouble(data);
                if (!isDouble) {
                    errorDetail.append("Giá trị(Value) sản phẩm không đúng định dạng số; ");
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
                "template" + File.separatorChar + Constants.TEMPLATE_STOCK_IMPORT_FAILS).getInputStream();
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

            XSSFCell cell111 = row1.createCell(11);
            cell111.setCellStyle(cellStyle);
            cell111.setCellValue(errorLst.get(i).getErrDetails());

        }
        file.close();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateTime = simpleDateFormat.format(date);
        String pathSave = environment.getRequiredProperty("spring.import_properties.folder_upload") + File.separatorChar + Constants.FOLDER_SAVE_TEMPLATE_ERR + File.separatorChar + dateTime  ;
        File file1 = new File(pathSave);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        File out = new File(pathSave + File.separatorChar + Constants.TEMPLATE_STOCK_IMPORT_FAILS);

        FileOutputStream outFile = new FileOutputStream(out);
        workbook.write(outFile);
        workbook.close();
        outFile.close();
        String pathResult = EncryptUtils.encryptFileUploadPath(Constants.TEMPLATE_STOCK_IMPORT_FAILS);
        return pathResult;
    }

}
