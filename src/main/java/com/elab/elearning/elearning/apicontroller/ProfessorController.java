package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.File;
import com.elab.elearning.elearning.entity.FileDB;
import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.message.ResponseMessage;
import com.elab.elearning.elearning.model.DocumentType;
import com.elab.elearning.elearning.model.UploadDocumentRequest;
import com.elab.elearning.elearning.repository.FileRepository;
import com.elab.elearning.elearning.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('PROFESSOR') or hasAuthority('STUDENT')")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    CourseService courseService;
    @Autowired
    TdService tdService;
    @Autowired
    TPService tpService;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    FileRepository fileRepository;


    @GetMapping(value = "/professor/{id}/module")
    @Operation(summary = "get all modules a professor teach", security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Set<Module>> getAssociatedModules(@PathVariable("id") Long id) {

        Set<Module> modules = professorService.getAssociatedModules(id);

        return ResponseEntity.status(HttpStatus.OK).body(modules);

    }


    @PostMapping(value = "module/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "upload a module document", security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<FileDB> upload(@RequestParam("title") String title,
                                         @RequestParam("type") DocumentType documentType,
                                         @RequestParam("moduleCode") String modulecode,
                                         @RequestParam("file") MultipartFile file) {
        String message = "";

        Module module = moduleService.getModule(modulecode).get();


        if (documentType == DocumentType.COURSE) {

            try {

                FileDB fileDB = fileStorageService.store(file, title, documentType, module);
                courseService.save(file);
                message = "Uploaded the course successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(fileDB);
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileDB());
            }
        } else if (documentType == DocumentType.TD) {

            try {
                FileDB fileDB = fileStorageService.store(file, title, documentType, module);
                tdService.save(file);
                message = "Uploaded the td successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(fileDB);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileDB());
            }

        } else {

            try {

                FileDB fileDB = fileStorageService.store(file, title, documentType, module);
                tpService.save(file);
                message = "Uploaded the tp successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(fileDB);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileDB());
            }
        }

    }


    @PostMapping(value = "{modulecode}/{type}/{id}")
    @Operation(summary = "delete a module document", security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<ResponseMessage> deleteDocument(@PathVariable("modulecode") String modulecode,
                                                          @PathVariable("type") DocumentType documentType,
                                                          @PathVariable("id") String id) {
        String message = "";

        Module module = moduleService.getModule(modulecode).get();


        if (documentType == DocumentType.COURSE) {

            try {

                Optional<FileDB> fileDB = fileRepository.findById(id);
                courseService.delete(fileDB.get().getName());
                fileRepository.deleteById(id);

                message = "deleted the course successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {

                message = "course couldn't be deleted";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } else if (documentType == DocumentType.TD) {


            try {

                Optional<FileDB> fileDB = fileRepository.findById(id);
                tdService.delete(fileDB.get().getName());
                fileRepository.deleteById(id);

                message = "deleted the td successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {

                message = "td couldn't be deleted";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }else{


                try {

                    Optional<FileDB> fileDB = fileRepository.findById(id);
                    tpService.delete(fileDB.get().getName());
                    fileRepository.deleteById(id);

                    message = "deleted the tp successfully";
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {

                    message = "tp couldn't be deleted";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }


            }

        }


    }


