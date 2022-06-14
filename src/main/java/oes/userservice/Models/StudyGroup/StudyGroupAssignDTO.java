package oes.userservice.Models.StudyGroup;

import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class StudyGroupAssignDTO {
    private CompositeKeyGroup key;
    private String moduleId;
}
