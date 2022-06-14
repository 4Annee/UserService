package oes.userservice.Controllers;

import oes.userservice.Entities.Assessment;
import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.Module;
import oes.userservice.Entities.StudyGroup;
import oes.userservice.Models.StudyGroup.SectionDTO;
import oes.userservice.Models.StudyGroup.StudyGroupAssignDTO;
import oes.userservice.Models.StudyGroup.StudyGroupCreationDTO;
import oes.userservice.Proxies.ExamServiceProxy;
import oes.userservice.Repositories.ModuleRepository;
import oes.userservice.Repositories.StudyGroupRepository;
import oes.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/StudyGroups")
public class StudyGroupController {
    @Autowired
    UserRepository repo;
    @Autowired
    ModuleRepository mrepo;
    @Autowired
    StudyGroupRepository srepo;
    @Autowired
    ExamServiceProxy proxy;

    @PostMapping("/")
    public StudyGroupCreationDTO createNewStudyGroup(@RequestBody StudyGroupCreationDTO dto){
        srepo.save(new StudyGroup(new CompositeKeyGroup(dto.getYear(),dto.getSection(),dto.getGroup()),null,null,null));
        return dto;
    }

    @DeleteMapping("/")
    public boolean deleteStudyGroup(@RequestBody CompositeKeyGroup dto){
        srepo.deleteById(dto);
        return true;
    }

    @GetMapping("/")
    public List<Integer> getYears(){
        return srepo.getYears();
    }

    @GetMapping("/{year}")
    public List<SectionDTO> getSectionAndGroups(@PathVariable int year){
        List<StudyGroup> groups = srepo.findStudyGroupsByIdYear(year);
        List<SectionDTO> sectionDTOS = new ArrayList<>();
        for (StudyGroup g :
                groups) {
            var gr = sectionDTOS.stream().filter(s->s.getSection()==g.getId().getSection()).findFirst();
            if(gr.isPresent()){
                gr.get().getGroups().add(g.getId().getGroup());
            }else{
                var el = new SectionDTO(g.getId().getSection(),new TreeSet<>());
                el.getGroups().add(g.getId().getGroup());
                sectionDTOS.add(el);
            }
        }
        return sectionDTOS;
    }

    @PostMapping("/Assign")
    public boolean groupStudyModule(@RequestBody StudyGroupAssignDTO dto){
        StudyGroup s = srepo.findById(dto.getKey()).get();
        Module m = mrepo.findById(dto.getModuleId()).get();
        s.getStudyModules().add(m);
        srepo.save(s);
        return true;
    }

    @GetMapping("/GetGroupExams")
    public StudyGroup getGroupExams(@RequestBody CompositeKeyGroup key){
        StudyGroup sg = srepo.findById(key).get();
        List<String> modules = sg.getStudyModules().stream().map(Module::getId).collect(Collectors.toList());
        sg.setAssessments(proxy.getAssessments((String[]) modules.toArray()));
        return sg;
    }
}
