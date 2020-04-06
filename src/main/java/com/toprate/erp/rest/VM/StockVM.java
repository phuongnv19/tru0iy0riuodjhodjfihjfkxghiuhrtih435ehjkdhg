package com.toprate.erp.rest.VM;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.dto.StockDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class StockVM  {

    private Long stockId;

    private Long stockParentId;

    private String stockName;

    private String stockCategory;

    private Long stockInitQuatity;

    private String stockCode;

    private Long stockQuantity;

    private String stockType;

    private Long stockUnit;

    private Long stockStatus;

    private String stockLocation;

    private Double stockValue;

    private String stockDescription;

    private String stockImage;

    private Long createUserId;

    private Date createDate;

    private Date modifiedDate;
    private Long locationId;

    public StockDTO toDto(){
        StockDTO stock = new StockDTO();
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
        return  stock;

    }
}
