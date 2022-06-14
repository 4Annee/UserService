package oes.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "studygroup",schema = "mainschema")
public class StudyGroup {
    @EmbeddedId
    private CompositeKeyGroup id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group")
    private List<User> students;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Module> studyModules;

    @Transient
    private List<Assessment> assessments;
}
