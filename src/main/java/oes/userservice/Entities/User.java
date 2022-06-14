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
@Table(name = "user",schema = "mainschema")
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
    private StudyGroup group;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Module> modules;
}
