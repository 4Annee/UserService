package oes.userservice.Controllers;

import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.StudyGroup;
import oes.userservice.Repositories.ModuleRepository;
import oes.userservice.Repositories.StudyGroupRepository;
import oes.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Scores")
public class ScoresController {
    @Autowired
    UserRepository repo;
    @Autowired
    ModuleRepository mrepo;
    @Autowired
    StudyGroupRepository srepo;

    @GetMapping("/year/{year}")
    public List<StudyGroup> getUsersByYearId(@PathVariable("year")int year){
        return srepo.findStudyGroupsByIdYear(year);
    }

    @GetMapping("/year/{year}/section/{id}")
    public List<StudyGroup> getUsersBySectionId(@PathVariable("year")int year,@PathVariable("id")String id){
        return srepo.findStudyGroupsByIdYearAndIdSection(year,id);
    }
    @GetMapping("/year/{year}/section/{section}/group/{group}")
    public StudyGroup getUsersByModuleId(@PathVariable("year")int year,@PathVariable("section")String section,@PathVariable("group")int group){
        return srepo.findById(new CompositeKeyGroup(year,section,group)).get();
    }
}
