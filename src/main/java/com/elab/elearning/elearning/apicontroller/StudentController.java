package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.entity.File;
import com.elab.elearning.elearning.service.CourseService;
import com.elab.elearning.elearning.service.FileService;
import com.elab.elearning.elearning.service.TPService;
import com.elab.elearning.elearning.service.TdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('PROFESSOR') or hasAuthority('STUDENT')")
public class StudentController {

    @Autowired
    CourseService courseService;
    @Autowired
    TdService tdService;
    @Autowired
    TPService tpService;
    
    @GetMapping("/course")
    @Operation(summary = "retrieve all courses",security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<File>> getListCourses() {
        List<File> file = courseService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getCourse", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/course/{filename:.+}")
    @Operation(summary =  "get a course",security = {@SecurityRequirement(name = "bearer-key")})
    public @ResponseBody ResponseEntity<Resource> getCourse(@PathVariable String filename) {
        Resource file = courseService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/td")
    @Operation(summary =  "get all tds",security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<File>> getListTds() {
        List<File> file = tdService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getTD", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/td/{filename:.+}")
    @Operation(summary =  "get a signle td",security = {@SecurityRequirement(name = "bearer-key")})
    public @ResponseBody ResponseEntity<Resource> getTD(@PathVariable String filename) {
        Resource file = tdService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/tp")
    @Operation(summary =  "get all TPs",security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<File>> getListTps() {
        List<File> file = tpService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(StudentController.class, "getTD", path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @GetMapping("/tp/{filename:.+}")
    @Operation(summary =  "get a signle TP",security = {@SecurityRequirement(name = "bearer-key")})
    public @ResponseBody ResponseEntity<Resource> getTP(@PathVariable String filename) {
        Resource file = tdService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }





    @GetMapping
    String goStudent() {

        return "WELCOME Student!";

    }
}
