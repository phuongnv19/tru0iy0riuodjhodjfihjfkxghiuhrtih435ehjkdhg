package com.toprate.erp.model;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.dto.StockDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOCK")
@Data
public class Stock {

    @Id
    @SequenceGenerator(name="pk_stock_sequence",sequenceName="stock_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_stock_sequence")
//    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "stock_seq")
    @Column(name = "STOCK_ID")
    private Long stockId;

    @Column(name = "STOCK_PARENT_ID")
    private Long stockParentId;

    @Column(name = "STOCK_NAME")
    private String stockName;

    @Column(name = "STOCK_CATEGORY")
    private String stockCategory;

    @Column(name = "STOCK_INIT_QUATITY")
    private Long stockInitQuatity;

    @Column(name = "STOCK_CODE")
    private String stockCode;

    @Column(name = "STOCK_QUANTITY")
    private Long stockQuantity;

    @Column(name = "STOCK_TYPE")
    private String stockType;

    @Column(name = "STOCK_UNIT")
    private Long stockUnit;

    @Column(name = "STOCK_STATUS")
    private Long stockStatus;

    @Column(name = "STOCK_LOCATION")
    private String stockLocation;


    @Column(name = "STOCK_VALUE")
    private Double stockValue;

    @Column(name = "STOCK_DESCRIPTION")
    private String stockDescription;

    @Column(name = "STOCK_IMAGE")
    private String stockImage;

    @Column(name = "CREATE_USER_ID")
    private Long createUserId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;
    @Column(name = "LOCATION_ID")
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
