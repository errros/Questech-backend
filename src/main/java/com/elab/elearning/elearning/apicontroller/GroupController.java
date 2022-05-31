package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.Group;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.model.GroupId;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/group")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class GroupController {


    @Autowired
    private GroupService groupService;

    @GetMapping
    @Operation(summary = "retrieve all groups", security = {@SecurityRequirement(name = "bearer-key")})
    List<Group> getAllGroups() {

        return groupService.getAllGroups();

    }

    @GetMapping("/{promo}")
    @Operation(summary = "retrieve all groups of a specific promo", security = {@SecurityRequirement(name = "bearer-key")})
    List<Group> getAllGroupsOfPromo(@PathVariable("promo") Promo promo) {

        return groupService.getAllGroupsOfPromo(promo);

    }



    @GetMapping(value = "/{promo}/{id}/student")
    @Operation(summary = "retrieve students of a given group", security = {@SecurityRequirement(name = "bearer-key")})
    List<Student> getGroupStudents(@PathVariable("promo") Promo promo , @PathVariable("id") Long id) {

        return groupService.getGroupStudents(promo,id);

    }

    @Operation(summary = "add a group",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping
    public Group add(@RequestBody Group group ) {

        return groupService.add(group);

    }



    @DeleteMapping("/{promo}/{id}")
    @Operation(summary = "delete a group", security = {@SecurityRequirement(name = "bearer-key")})
    public void delete(@PathVariable("promo") Promo promo , @PathVariable("id") Long id) {

        groupService.delete(promo,id);
    }

    @Operation(summary = "add a student to a group",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/{promo}/{id}/student")
    public Student addStudentToGroup(@PathVariable("promo") Promo promo , @PathVariable("id")  Long id ,
                                   @RequestParam("studentid") Long studentid) {

        return groupService.addStudentToGroup(promo,id,studentid);

    }


    @Operation(summary = "delete student from a group",  security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping("/{promo}/{id}/student")
    public Student deleteStudentFromGroup(@PathVariable("promo") Promo promo , @PathVariable("id")  Long groupid ,
                                     @RequestParam("studentid") Long studentid) {

        return groupService.deleteStudentFromGroup(promo,groupid,studentid);

    }






}
