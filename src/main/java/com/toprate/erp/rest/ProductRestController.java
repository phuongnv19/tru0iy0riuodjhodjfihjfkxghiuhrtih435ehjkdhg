package com.toprate.erp.rest;

import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.rest.VM.ProductVM;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.service.impl.ProductServiceImpl;
import com.toprate.erp.util.Constants;
import com.toprate.erp.util.UFile;
import com.toprate.erp.util.UString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

@RestController
public class ProductRestController extends AbstractRestController {

    @Autowired
    private Environment environment;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/product/doSearch")
    public ResponseEntity doSearch(@Valid @RequestBody ProductDTO productObject) {
//        try {
        return new ResponseEntity<>(productServiceImpl.doSearch(productObject), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.OK);
//        }
    }

    @PostMapping("/masterCalender/doSearch")
    public ResponseEntity doSearchForMasterCalender(@Valid @RequestBody ProductDTO productObject) {
//        try {
        return new ResponseEntity<>(productServiceImpl.doSearchForMasterCalender(productObject), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.OK);
//        }
    }

    @PostMapping("product/getDetailForMasterCalender")
    public ResponseEntity getDetailForMasterCalender(@Valid @RequestBody ProductVM productObject) {
//        try {
        return new ResponseEntity<>(productServiceImpl.getDetailForMasterCalender(productObject.toDto().getProductId()), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.OK);
//        }
    }

    @PostMapping("/product/save")
    public ResponseEntity save(@Valid @RequestBody ProductVM productObject) throws Exception {
//        try {
        return new ResponseEntity<>(productServiceImpl.insert(productObject.toDto()), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.OK);
//        }
    }

    @PostMapping("/product/update")
    public ResponseEntity update(@Valid @RequestBody ProductVM productObject) throws Exception {
//        try {
        return new ResponseEntity<>(productServiceImpl.update(productObject.toDto()), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.OK);
//        }
    }

    @PostMapping("/product/import")
    public ResponseEntity importSaleInventory(@RequestParam("file") MultipartFile file) throws Exception {
        // TODO Auto-generated method stub
        String filePath;

        String fileName = file.getOriginalFilename();

        if (!isExtendAllowSave(fileName)) {
            throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
        }

        try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            filePath = UFile.writeToFileServerATTT2(inputStream, fileName, environment.getRequiredProperty("spring.import_properties.folder_upload") + File.separatorChar + Constants.FOLDER_SAVE_FILE_IMPORT_PRODUCT);
        } catch (Exception ex) {
            throw new BusinessException("Loi khi save file", ex);
        }
        try {
            return new ResponseEntity<>(productServiceImpl.importSaleInventory((environment.getRequiredProperty("spring.import_properties.folder_upload") + File.separatorChar + Constants.FOLDER_SAVE_FILE_IMPORT_PRODUCT + filePath)), HttpStatus.OK);

        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    private boolean isExtendAllowSave(String fileName) {
        return UString.isExtendAllowSave(fileName, environment.getRequiredProperty("spring.import_properties.allow_file_ext"));
    }


}
