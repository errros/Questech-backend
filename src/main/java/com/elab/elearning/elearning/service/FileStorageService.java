package com.elab.elearning.elearning.service;

import com.elab.elearning.elearning.entity.FileDB;
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

    public FileDB store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType());
        System.out.println(fileDB);
        return fileDBRepository.save(fileDB);
    }
}
