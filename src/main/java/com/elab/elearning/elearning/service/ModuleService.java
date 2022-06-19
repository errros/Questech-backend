package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //deleting the module from the assistants
          professorRepository.findAll().forEach(p->{
              boolean b = p.getModulesAssist().removeIf(t->t.getCode().equals(code));
              if(b){
               professorRepository.save(p);   
              }

          });

        //deleting the module from responsable
        professorRepository.findAll().forEach(p->{
            boolean b = p.getModulesResponsable().removeIf(t->t.getCode().equals(code));
            if(b){
                professorRepository.save(p);
            }

        });


//delete the module
        moduleRepository.deleteById(code);




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


         return module;

     }else {
         throw new OpenApiResourceNotFoundException("No existing module with such a code!");
     }
    }

    public Module addAssistantToModule(String code, Long id) {
        Optional<Module> module = moduleRepository.findById(code);
        if(module.isPresent()){
            Optional<Professor> professor = professorRepository.findById(id);
            if(professor.isPresent()){

                professor.get().getModulesAssist().add(module.get());
                module.get().getAssistants().add(professor.get());
                moduleRepository.save(module.get());
                professorRepository.save(professor.get());

                return module.get();

            }


        }
        throw new OpenApiResourceNotFoundException("adding assistant to module operation failed");


    }

    public Module deleteAssistantFromModule(String code, Long id) {
        Optional<Module> module = moduleRepository.findById(code);


        if (module.isPresent()) {
            Optional<Professor> professor = professorRepository.findById(id);
            if (professor.isPresent()) {

                module.get().getAssistants().removeIf(a -> a.getId() == id);
                professor.get().getModulesAssist().removeIf(m -> m.getCode().equals(code));
                moduleRepository.save(module.get());
                professorRepository.save(professor.get());

                return module.get();
            }


        }
           throw new OpenApiResourceNotFoundException("deleting assistant from module operation failed");
    }





    public Module deleteResponsableOfModule(String code) {


        Optional<Module> module = moduleRepository.findById(code);


        if (module.isPresent()) {
                module.get().setResponsable(null);
                professorRepository.findAll().forEach(p->{

                    p.getModulesResponsable().removeIf(m->m.getCode().equals(code));
                    professorRepository.save(p);
                });
                moduleRepository.save(module.get());


                return module.get();
            }


        throw new OpenApiResourceNotFoundException("No existing module with such a code!");

        }






    public Module setResponsableToModule(String code, Long id) {

        Optional<Module> module = moduleRepository.findById(code);


        if (module.isPresent()) {
            Optional<Professor> professor = professorRepository.findById(id);
            if (professor.isPresent()) {

                module.get().setResponsable(professor.get());
                professor.get().getModulesResponsable().add(module.get());
                moduleRepository.save(module.get());
                professorRepository.save(professor.get());

                return module.get();
            }


        }
        throw new OpenApiResourceNotFoundException("deleting assistant from module operation failed");




    }

    public Professor getModuleResponsable(String code) {
        Optional<Module> m = moduleRepository.findById(code);
        if(m.isPresent()){

            return m.get().getResponsable();
        }else{
            throw new OpenApiResourceNotFoundException("there's no given module with such a code!");
        }

    }

    public Set<Professor> getAssistantsOfModule(String code) {
        Optional<Module> m = moduleRepository.findById(code);
        if(m.isPresent()){

            return Set.copyOf(m.get().getAssistants());
        }else{
            throw new OpenApiResourceNotFoundException("there's no given module with such a code!");
        }



    }

    public List<Module> getModulesOfAPromo(Promo promo) {

      List<Module> modules = moduleRepository.findByPromo(promo);
      return modules;

    }
}
