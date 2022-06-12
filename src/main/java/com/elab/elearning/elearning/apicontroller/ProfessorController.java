package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.File;
import com.elab.elearning.elearning.message.ResponseMessage;
import com.elab.elearning.elearning.service.CourseService;
import com.elab.elearning.elearning.service.FileService;
import com.elab.elearning.elearning.service.FileStorageService;
import com.elab.elearning.elearning.service.TdService;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/professor")
@PreAuthorize(value = "hasAuthority('PROFESSOR')")
public class ProfessorController {

    @Autowired
    CourseService storageService;
    @Autowired
    TdService tdService;
    @Autowired
    FileStorageService fileStorageService;
    @PostMapping("/upload-course")
    public ResponseEntity<ResponseMessage> uploadCourse(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            fileStorageService.store(file);
            message = "Uploaded the course successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @PostMapping("/upload-td")
    public ResponseEntity<ResponseMessage> uploadTD(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            tdService.save(file);
            fileStorageService.store(file);
            message = "Uploaded the td successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
