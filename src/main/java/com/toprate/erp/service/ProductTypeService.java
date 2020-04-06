package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.dto.ProductTypeDTO;
import com.toprate.erp.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductTypeDTO> getListProductType(Long categoryId);

    List<ProductType> findAll();

    List<ProductTypeDTO> autoCompleteSearch(SearchDTO obj);
}
