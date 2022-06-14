package oes.userservice.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oes.userservice.Entities.Enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fullname;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Role role;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studygroup;

    @JsonIgnore
    @ManyToMany(mappedBy = "teachers")
    @JoinTable(name = "user_teaching_modules",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "teaching_modules_id"))
    private List<Module> modules;
}
