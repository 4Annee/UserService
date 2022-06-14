package oes.userservice.Controllers;

import oes.userservice.Entities.StudyGroup;
import oes.userservice.Models.Module.ModuleCreationDTO;
import oes.userservice.Repositories.ModuleRepository;
import oes.userservice.Repositories.StudyGroupRepository;
import oes.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oes.userservice.Entities.Module;

import java.util.List;


@RestController
@RequestMapping("/api/Module")
public class ModuleController {
    @Autowired
    UserRepository repo;
    @Autowired
    ModuleRepository mrepo;
    @Autowired
    StudyGroupRepository srepo;

    @PostMapping("/")
    public Module createNewModule(@RequestBody ModuleCreationDTO dto){
        return mrepo.save(new Module(null,dto.getModulename(),dto.getSemester(),null,null));
    }

    @DeleteMapping("/{id}")
    public boolean deleteModule(@PathVariable("id")String id){
        mrepo.deleteById(id);
        return true;
    }

    @GetMapping("/year/{year}/section/{section}")
    public List<Module> getYearModules(@PathVariable("year")int year,@PathVariable("section")String section){
        StudyGroup g = srepo.findStudyGroupsByIdYearAndIdSection(year,section).get(0);
        return g.getStudyModules();
    }

    @GetMapping("/year/{year}/section/{section}/semester/{sem}")
    public List<Module> getYearSemesterModules(@PathVariable("year")int year,@PathVariable("section")String section,@PathVariable("sem")int semeseter){
        StudyGroup g = srepo.findStudyGroupsByIdYearAndIdSection(year,section).get(0);
        return g.getStudyModules().stream().filter(o->o.getSemester()==semeseter).toList();
    }

}
