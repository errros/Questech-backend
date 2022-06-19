package com.elab.elearning.elearning.service;

import com.elab.elearning.elearning.entity.FileDB;
import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.model.DocumentType;
import com.elab.elearning.elearning.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileDBRepository;

    public FileDB store(MultipartFile file, String title, DocumentType type, Module module) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB fileDB = new FileDB(fileName,title,type,module);
        fileDB.getModule().getDocuments().add(fileDB);



        return     fileDBRepository.save(fileDB);

    }
}
