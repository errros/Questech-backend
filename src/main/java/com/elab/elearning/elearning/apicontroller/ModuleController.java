package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/module")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    @Operation(summary = "retrieve all modules", security = {@SecurityRequirement(name = "bearer-key")})
    List<Module> getAllModules() {

        return moduleService.getAllModules();

    }


    @GetMapping(value = "/{code}")
    @Operation(summary = "retrieve a single module", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<Module> getModule(@PathVariable("code") String code) {

        return moduleService.getModule(code);

    }

    @Operation(summary = "add a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping
    public Module add( @RequestBody Module module ) {

        return moduleService.add(module);

    }

    @Operation(summary = "add a professor to a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/professor/{code}")
    public Module addProfessorToModule(@PathVariable("code") String code , @RequestParam("id") Long id) {

        return moduleService.addProfessorToModule(code,id);

    }

    @Operation(summary = "delete a professor from a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping("/professor/{code}")
    public Module deleteProfessorFromModule(@PathVariable("code") String code , @RequestParam("id") Long id) {

        return moduleService.deleteProfessorFromModule(code,id);

    }







    @DeleteMapping("/{code}")
    @Operation(summary = "delete a module", security = {@SecurityRequirement(name = "bearer-key")})
    public void delete(@PathVariable("code") String code) {
        moduleService.delete(code);
    }





    @PutMapping("/{code}")
    @Operation(summary = "update module", security = {@SecurityRequirement(name = "bearer-key")})
    public Module update(@RequestBody Module module) {

        return  moduleService.update(module);


    }






}
