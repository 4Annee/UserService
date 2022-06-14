package oes.userservice.Models.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class StudyGroupCreationDTO {
    private int year;
    private String section;
    private int group;
}
