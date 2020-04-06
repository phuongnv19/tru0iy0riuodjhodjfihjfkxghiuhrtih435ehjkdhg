package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.dto.StockDTO;

import java.util.List;


public interface StockService {
    List<StockDTO> getListStock();
    DataListDTO doSearch(StockDTO obj);
    StockDTO insert(StockDTO obj) throws Exception;
    StockDTO update(StockDTO obj) throws Exception;
    public StockDTO importStockInventory(String fileInput) throws Exception;
}

