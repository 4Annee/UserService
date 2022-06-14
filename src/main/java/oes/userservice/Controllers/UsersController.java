package oes.userservice.Controllers;

import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.Module;
import oes.userservice.Entities.StudyGroup;
import oes.userservice.Entities.User;
import oes.userservice.Models.User.UserCreationDTO;
import oes.userservice.Models.User.UserDTO;
import oes.userservice.Models.User.UserLoginDTO;
import oes.userservice.Repositories.ModuleRepository;
import oes.userservice.Repositories.StudyGroupRepository;
import oes.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/User")
public class UsersController {
    @Autowired
    UserRepository repo;
    @Autowired
    ModuleRepository mrepo;
    @Autowired
    StudyGroupRepository srepo;

    @GetMapping("/Auth")
    public UserDTO loginUser(@RequestBody UserLoginDTO login){
        List<User> users = repo.findUsersByEmail(login.getEmail());
        if(users.get(0).getPassword() == login.getPassword()){
            User user1 = users.get(0);
            return new UserDTO(user1.getId(),user1.getRole(),user1.getStudygroup().getId().getYear(),
                    user1.getStudygroup().getId().getSection(),user1.getStudygroup().getId().getGroup());
        }
        return null;
    }

//    No Getting all users
//    GET "/:id" get user by id
//    GET "/module/:id" get users studying module
//    GET "/group/:id" get users by group id ….. same for /Section and /year
//    POST "/" add, PUT "/:id" Update, DELETE "/:id" delete

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id")String id){
        return repo.findById(id).get();
    }

    @GetMapping("/module/{id}")
    public Module getUsersByModuleId(@PathVariable("id")String id){
        return mrepo.findById(id).get();
    }

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
        return srepo.findStudyGroupByIdYearAndIdSectionAndIdGroup(year,section,group);
    }

    @PostMapping("/")
    public User addNewUser(@RequestBody UserCreationDTO dto){
        if(dto.getStudygroup().isPresent()){
            StudyGroup g = srepo.findById(dto.getStudygroup().get()).get();
            return repo.save(new User(null,dto.getFullname(), dto.getEmail(), dto.getPassword(), dto.getRole(),g,null));
        }else{
            return repo.save(new User(null,dto.getFullname(), dto.getEmail(), dto.getPassword(), dto.getRole(),null,null));
        }
    }


}
