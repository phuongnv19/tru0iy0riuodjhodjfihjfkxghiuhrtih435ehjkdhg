package com.toprate.erp.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class ExcelUtils {

    public static void exportExcel(InputStream is, Context context, OutputStream os) throws IOException {
        JxlsHelper.getInstance().processTemplate(is, os, context);
        is.close();
        os.close();
    }

    public static boolean isMapAllValueEmpty(Map<Integer, String> map) {
        for (String v : map.values()) {
            if (v != null && !v.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static Map<Integer, String> getDataRowExcel(Row row, int startCol, int endCol) {
        Map<Integer, String> map = new HashMap<>();
        // For each startRow - endRow, iterate through each columns
        for (int i = startCol; i <= endCol; i++) {
            if (row != null) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    map.put(cell.getColumnIndex(), getCellValue(cell));
                } else {
                    map.put(i, "");
                }
            }

        }
        return map;
    }


    public static List<Map<Integer, String>> getDataExcel(int startRow, int startCol, int endCol, XSSFWorkbook workbook) throws IOException {
        List<Map<Integer, String>> listImportResult = new ArrayList<>();
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        if (lastRow >= startRow) {
            for (int i = startRow; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                Map<Integer, String> mapRow = getDataRowExcel(row, startCol, endCol);
                if (!isMapAllValueEmpty(mapRow)) {
                    listImportResult.add(mapRow);
                }
            }
        }
        workbook.close();
        return listImportResult;
    }

    public static Map<Integer, String> getHeaderExcel(int rowHeader, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowHeader);
        Map<Integer, String> mapHeader = getDataRowExcel(row);
        if (!isMapAllValueEmpty(mapHeader)) {
            return mapHeader;
        }
        return new HashMap<>();
    }

    private static Map<Integer, String> getDataRowExcel(Row row) {
        Map<Integer, String> map = new HashMap<>();
        //For each row, iterate through all the columns
        if (row != null) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                if (cell != null) {
                    map.put(cell.getColumnIndex(), getCellValue(cell));
                }

            }
        }

        return map;
    }

    public static String getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        } else if (cellType == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType == CellType.BLANK) {
            return "";
        }
        return "";
    }


    public static XSSFCellStyle styleHeader(XSSFSheet sheet) {
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontHeightInPoints((short) 12);
        XSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    public static XSSFCellStyle styleErrorImport(XSSFSheet sheet) {
        XSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setColor(XSSFFont.COLOR_RED);
        cellStyle.setFont(font);
        return cellStyle;
    }


    public static void createHeader(XSSFSheet sheet, Map<Integer, String> mapHeader) {
        Row row = sheet.createRow(0);
        mapHeader.forEach((column, value) -> {
            //sheet.setColumnWidth(column, 20 * 256);
            Cell cell = row.createCell(column);
            cell.setCellValue(value);
            cell.setCellStyle(styleHeader(sheet));
        });
    }

    public static void autoSizeColumn(XSSFSheet sheet, Set<Integer> columns) {
        columns.forEach(column -> sheet.autoSizeColumn(column));
    }

    public static void writeExcel(Map<Integer, String> mapHeader, List<Map<Integer, String>> listImportError, String fileExportPath) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("customer");
        createHeader(sheet, mapHeader);

        int rowNum = 1;
        for (Map<Integer, String> mapRow : listImportError) {
            Row row = sheet.createRow(rowNum++);
            mapRow.forEach((column, value) -> {
                Cell cell = row.createCell(column);
                cell.setCellValue(value);
                if (MessageUtils.getMessage("error").equals(mapHeader.get(column))) {
                    cell.setCellStyle(styleErrorImport(sheet));
                }
            });
        }

        autoSizeColumn(sheet, mapHeader.keySet());

        FileOutputStream outputStream = new FileOutputStream(fileExportPath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public static boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }
}
