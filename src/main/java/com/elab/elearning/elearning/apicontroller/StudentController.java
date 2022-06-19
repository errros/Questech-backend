package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.entity.File;
import com.elab.elearning.elearning.entity.FileDB;
import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.model.DocumentType;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.ModuleRepository;
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
    ModuleRepository moduleRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    TdService tdService;
    @Autowired
    TPService tpService;



    @GetMapping("/module/{code}/{type}")
    @Operation(summary = "retrieve all courses",security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<FileDB>> getListCourses(@PathVariable("code") String code ,
                                                     @PathVariable("type") DocumentType documentType                                  ) {


        List<FileDB> documents ;
        Module module = moduleRepository.getById(code);

        if(documentType == DocumentType.COURSE) {
             documents = courseService.getAllCourses(module);



        }else if(documentType == DocumentType.TD){
             documents = tdService.getAllTds(module);



        }else {

            documents = tpService.getAllTPs(module);

        }


        return ResponseEntity.status(HttpStatus.OK).body(documents);

    }
    @GetMapping("/{type}/{filename:.+}")
    @Operation(summary =  "get a document",security = {@SecurityRequirement(name = "bearer-key")})
    public @ResponseBody ResponseEntity<Resource> getCourse(@PathVariable("type") DocumentType documentType,
                                                            @PathVariable String filename) {
        if(documentType == DocumentType.COURSE) {
            Resource file = courseService.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);

        } else if(documentType == DocumentType.TD){
            Resource file = tdService.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);


         } else {
            Resource file = tdService.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);


        }

    }



    @GetMapping
    String goStudent() {

        return "WELCOME Student!";

    }
}
