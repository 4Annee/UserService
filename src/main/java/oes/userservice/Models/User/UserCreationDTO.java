package oes.userservice.Models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.Enums.Role;
import oes.userservice.Entities.Module;
import oes.userservice.Entities.StudyGroup;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserCreationDTO {
    private String fullname;
    private String email;
    private String password;

    private Role role;

    private Optional<CompositeKeyGroup> studygroup;
}
