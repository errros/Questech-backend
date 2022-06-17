package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.model.Promo;
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
//module/assitants
//module/responsable

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

    @GetMapping(value = "/promo")
    @Operation(summary = "retrieve all modules of a given promo", security = {@SecurityRequirement(name = "bearer-key")})
    List<Module> getModulesOfAPromo(@RequestParam("promo") Promo promo) {

        return moduleService.getModulesOfAPromo(promo);

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




    @Operation(summary = "add an assitant professor to a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @PutMapping("/{code}/assistant/{id}")
    public Module addAssistantToModule(@PathVariable("code") String code , @PathVariable("id") Long id) {

        return moduleService.addAssistantToModule(code,id);

    }

    @Operation(summary = "delete an assitant professor from a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping("/{code}/assistant/{id}")
    public Module deleteAssistantFromModule(@PathVariable("code") String code , @PathVariable("id") Long id) {

        return moduleService.deleteAssistantFromModule(code,id);

    }



    @Operation(summary = "chargé de cour",  security = {@SecurityRequirement(name = "bearer-key")})
    @PutMapping("/{code}/responsable/{id}")
    public Module setResponsableOfModule(@PathVariable("code") String code , @PathVariable("id") Long id) {

        return moduleService.setResponsableToModule(code,id);

    }


    @Operation(summary = "retrieve responsable professor of a given module",  security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/{code}/responsable")
    public Professor getResponsableOfModule(@PathVariable("code") String code) {

        return moduleService.getModuleResponsable(code);

    }

    @Operation(summary = "retrieve assistant professors of a given module",  security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/{code}/assistant")
    public Set<Professor> getAssistantsOfModule(@PathVariable("code") String code) {

        return moduleService.getAssistantsOfModule(code);

    }

    @Operation(summary = "delete chargé cour",  security = {@SecurityRequirement(name = "bearer-key")})
    @PutMapping("/{code}/responsable")
    public Module deleteResponsableOfModule(@PathVariable("code") String code) {

        return moduleService.deleteResponsableOfModule(code);

    }







}
