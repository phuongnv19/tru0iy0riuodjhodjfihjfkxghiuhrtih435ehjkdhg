package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.CrmBookingDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.model.Product;

import java.util.List;


public interface ProductService {
    DataListDTO doSearch(ProductDTO obj);
    ProductDTO insert(ProductDTO obj) throws Exception;
    ProductDTO update(ProductDTO obj) throws Exception;
    ProductDTO importSaleInventory(String fileInput) throws Exception;
    DataListDTO doSearchForMasterCalender(ProductDTO obj);
    ProductDTO getDetailForMasterCalender(Long product_id);
    List<Product> findAll();
    List<ProductDTO> autoCompleteSearch(SearchDTO productDTO);
    DataListDTO doSearchForBooking(CrmBookingDTO obj);
}

