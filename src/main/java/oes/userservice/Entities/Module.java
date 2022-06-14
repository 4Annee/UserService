package oes.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "module",schema = "mainschema")
public class Module {
    @Id
    private String Id;
    private String name;
    private int semester;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> teachers;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<StudyGroup> studyGroups;
}
