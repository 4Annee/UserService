package oes.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Module {
    @Id
    private String Id;
    private String name;
    private int semester;
    @JsonIgnore
    @ManyToMany(mappedBy = "modules")
    private List<User> teachers;

    @ManyToMany(mappedBy = "studyModules")
    private List<StudyGroup> studyGroups;
}
