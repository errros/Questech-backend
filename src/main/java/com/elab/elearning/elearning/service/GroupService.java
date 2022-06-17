package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Group;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.model.GroupId;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.GroupRepository;
import com.elab.elearning.elearning.repository.StudentRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;


    public List<Group> getAllGroups() {

       return groupRepository.findAll();
    }

    public Group add(Group group) {
        return groupRepository.save(group);



    }


    public void delete(Promo promo , Long id) {
        GroupId gid = new GroupId(promo,id);
          Optional<Group> g =groupRepository.findById(gid);
          if(g.isPresent()){
              g.get().getStudents().forEach(s->s.setGroup(null));

          }else {
              throw new OpenApiResourceNotFoundException("can't find group with such a groupid to be deleted");
          }

          groupRepository.deleteById(gid);



    }


    public List<Student> getGroupStudents(Promo promo, Long id) {
        GroupId groupId = new GroupId(promo,id);

        Optional<Group> group= groupRepository.findById(groupId);
         if(group.isPresent()){
             return group.get().getStudents();
         }else {
             return new ArrayList<>();
         }

    }

    public String addStudentToGroup(Promo promo , Long groupid , Set<Long> studentids) {


        Optional<Group> group = groupRepository.findById(new GroupId(promo,groupid));



        if(group.isPresent()) {

            for (Long studentid : studentids) {
                Optional<Student> student = studentRepository.findById(studentid);
                if (student.isPresent()) {

                    group.get().getStudents().add(student.get());
                    student.get().setGroup(group.get());
                    studentRepository.save(student.get());
                    groupRepository.save(group.get());
                } else {
                    throw new OpenApiResourceNotFoundException("There's no student with sucn an id!");
                }


            }

        }

        return "Added successfully";

    }

    public String deleteStudentFromGroup(Promo promo, Long groupid, Set<Long> studentids) {

        Optional<Group> group = groupRepository.findById(new GroupId(promo,groupid));
        if(group.isPresent()){
            for(Long studentid : studentids ) {
                Optional<Student> student = studentRepository.findById(studentid);
                if (student.isPresent()) {
                    System.out.println(group.get().getStudents());
                    boolean b = group.get().getStudents().removeIf(t -> t.getId() == student.get().getId());


                    groupRepository.save(group.get());
                    if (b) {
                        student.get().setGroup(null);
                        studentRepository.save(student.get());
                    }

                } else {

                    throw new OpenApiResourceNotFoundException("There's no student with such an id!");
                }


            }
            return "Deleted successfully";
    }

        throw new OpenApiResourceNotFoundException("delete operation hadn't beendone");


    }


    public List<Group> getAllGroupsOfPromo(Promo promo) {
            return groupRepository.findByGroupIdPromo(promo);

    }
}
