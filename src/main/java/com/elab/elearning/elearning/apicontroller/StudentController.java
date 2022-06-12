package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.entity.File;
import com.elab.elearning.elearning.service.CourseService;
import com.elab.elearning.elearning.service.FileService;
import com.elab.elearning.elearning.service.TdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/student")
@PreAuthorize(value = "hasAuthority('STUDENT')")
public class StudentController {
    @Autowired
    CourseService storageService;
    @Autowired
    TdService tdService;
    @Autowired
    FileService fileService;
    @GetMapping("/courses")
    public ResponseEntity<List<File>> getListCourses() {
        List<File> file = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getCourse", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/courses/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getCourse(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/tds")
    public ResponseEntity<List<File>> getListTds() {
        List<File> file = tdService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getTD", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/tds/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getTD(@PathVariable String filename) {
        Resource file = tdService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/emploi")
    public ResponseEntity<List<File>> getListEmploi() {
        List<File> file = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getEmploi", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getEmploi(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping
    String goStudent() {

        return "WELCOME Student!";

    }
}
