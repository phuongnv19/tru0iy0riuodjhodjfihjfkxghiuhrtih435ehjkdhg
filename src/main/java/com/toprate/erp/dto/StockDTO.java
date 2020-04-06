package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO extends ErpBaseDTO {
    private Long stockId;

    @AdjHistory(field = "LOCATION_ID")
    private Long locationId;

    @AdjHistory(field = "STOCK_PARENT_ID")
    private Long stockParentId;

    @AdjHistory(field = "STOCK_NAME")
    private String stockName;

    @AdjHistory(field = "STOCK_CATEGORY")
    private String stockCategory;

    @AdjHistory(field = "STOCK_INIT_QUATITY")
    private Long stockInitQuatity;

    @AdjHistory(field = "STOCK_CODE")
    private String stockCode;

    @AdjHistory(field = "STOCK_QUANTITY")
    private Long stockQuantity;

    @AdjHistory(field = "STOCK_TYPE")
    private String stockType;

    @AdjHistory(field = "STOCK_UNIT")
    private Long stockUnit;

    @AdjHistory(field = "STOCK_STATUS")
    private Long stockStatus;

    @AdjHistory(field = "STOCK_LOCATION")
    private String stockLocation;

    @AdjHistory(field = "STOCK_VALUE")
    private Double stockValue;

    @AdjHistory(field = "STOCK_DESCRIPTION")
    private String stockDescription;

    @AdjHistory(field = "STOCK_IMAGE")
    private String stockImage;

    @AdjHistory(field = "CREATE_USER_ID")
    private Long createUserId;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "MODIFIED_DATE")
    private Date modifiedDate;

    private List<StockDTO> lstStockSuccess;

    List<ObjFailsDTO> workLstErr;

    private String pathError;

    private Long countSuccess;
    private Integer countFails;

    public Stock toModel() {
        Stock stock = new Stock();
        stock.setStockId(stockId);
        stock.setStockParentId(stockParentId);
        stock.setStockName(stockName);
        stock.setStockCategory(stockCategory);
        stock.setStockInitQuatity(stockInitQuatity);
        stock.setStockCode(stockCode);
        stock.setStockQuantity(stockQuantity);
        stock.setStockType(stockType);
        stock.setStockUnit(stockUnit);
        stock.setStockStatus(stockStatus);
        stock.setStockLocation(stockLocation);
        stock.setStockDescription(stockDescription);
        stock.setStockImage(stockImage);
        stock.setCreateUserId(createUserId);
        stock.setCreateDate(createDate);
        stock.setModifiedDate(modifiedDate);
        stock.setLocationId(locationId);
        stock.setStockValue(stockValue);
        return stock;

    }
}
