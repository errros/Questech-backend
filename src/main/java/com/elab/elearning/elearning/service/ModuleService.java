package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.*;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    public List<Module> getAllModules() {
        return moduleRepository.findAll();

    }



    public Module add(Module module) {

        Set<Professor> professors = new HashSet<>();
        moduleRepository.save(module);


        return module;
    }




    public void delete(String code) {


        moduleRepository.deleteById(code);
        System.out.println( professorRepository.findAll());




    }


    public Optional<Module> getModule(String code) {
        Optional<Module> m = Optional.of(moduleRepository.getById(code));
         return m;


    }

    public Module update(Module module) {

    Optional<Module> moduleOpt = moduleRepository.findById(module.getCode());

     if(moduleOpt.isPresent()) {
         Module m = moduleOpt.get();

         m.setDetailedName(module.getDetailedName());
         m.setCredit(module.getCredit());
         m.setCoefficient(module.getCoefficient());
         m.setSemester(module.getSemester());
         m.setPromo(module.getPromo());
         moduleRepository.save(m);



     }
      return module;
    }

    public Module addProfessorToModule(String code, Long id) {
       Optional<Module> module = moduleRepository.findById(code);
       if(module.isPresent()){
           Optional<Professor> professor = professorRepository.findById(id);
           if(professor.isPresent()){

               professor.get().getModules().add(module.get());
               module.get().getProfessors().add(professor.get());
               moduleRepository.save(module.get());
               professorRepository.save(professor.get());
           }


       }


        return module.get();
    }

    public Module deleteProfessorFromModule(String code, Long id) {

        Optional<Module> module = moduleRepository.findById(code);

        if(module.isPresent()){
            Optional<Professor> professor = professorRepository.findById(id);
            if(professor.isPresent()){

                module.get().getProfessors().remove(professor.get());
                professor.get().getModules().remove(module.get());
                moduleRepository.save(module.get());
                professorRepository.save(professor.get());
            }


        }


        return module.get();




    }
}
