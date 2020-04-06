package com.toprate.erp.rest;

import com.toprate.erp.dto.FileDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.util.EncryptUtils;
import com.toprate.erp.util.UFile;
import com.toprate.erp.util.UString;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class FileRestComtroller {
    @Autowired
    private Environment environment;

    @PostMapping("/common/uploadFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderUpload") String folderUpload) throws Exception {
        // TODO Auto-generated method stub
        String filePath;
        String fileName = file.getOriginalFilename();

        if (!isExtendAllowSave(fileName)) {
            throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
        }

        try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            filePath = UFile.writeToFileServerATTT2(inputStream, fileName, environment.getRequiredProperty("spring.import_properties.folder_upload") + File.separatorChar + folderUpload);
            String filePathReturn = EncryptUtils.encryptFileUploadPath(filePath);
            return new ResponseEntity<>(filePathReturn, HttpStatus.OK);
        } catch (Exception ex) {
            throw new BusinessException("Loi khi save file", ex);
        }
    }

    @PostMapping("/common/uploadFileMulti")
    public ResponseEntity uploadFileMulti(@RequestParam("files") MultipartFile[] files, @RequestParam("folderUpload") String folderUpload) throws Exception {
        // TODO Auto-generated method stub
        List<FileDTO> listPath= Lists.newArrayList();



        for(MultipartFile file :files) {

            String   fileName = file.getOriginalFilename();

            if (!isExtendAllowSave(fileName)) {
                throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
            }

            try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
                String  filePath = UFile.writeToFileServerATTT2(inputStream, fileName, environment.getRequiredProperty("spring.import_properties.folder_upload") + File.separatorChar + folderUpload);
                String  filePathReturn = EncryptUtils.encryptFileUploadPath(filePath);
                FileDTO fileDTO= new FileDTO();
                fileDTO.setFileName(fileName);
                fileDTO.setFileNameEncrypt(filePathReturn);
                listPath.add(fileDTO);
            } catch (Exception ex) {
                throw new BusinessException("Loi khi save file", ex);
            }
        }
        return new ResponseEntity<>(listPath, HttpStatus.OK);
    }

    private boolean isExtendAllowSave(String fileName) {
        return UString.isExtendAllowSave(fileName, environment.getRequiredProperty("spring.import_properties.allow_file_ext"));
    }

    @GetMapping("/common/downloadFile")
    public ResponseEntity downloadFileATTT(@RequestParam("fileName") String fileName, @RequestParam("folderUpload") String folderUpload) throws Exception {
        fileName = EncryptUtils.decryptFileUploadPath(fileName);
        String baseFolder = environment.getRequiredProperty("spring.import_properties.folder_upload");
        File file = new File(baseFolder + File.separatorChar + folderUpload + File.separatorChar + fileName);
        if (!file.exists()) {
            throw new BusinessException("File not found, file_name:" + fileName);
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        int lastIndex = fileName.lastIndexOf(File.separatorChar);
        String fileNameReturn = fileName.substring(lastIndex + 1);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=" + fileNameReturn);
        httpHeaders.add("Content-Type", "application/octet-stream");
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }
}
