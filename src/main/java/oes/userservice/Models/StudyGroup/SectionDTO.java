package oes.userservice.Models.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class SectionDTO {
    private String section;
    private Set<Integer> groups;
}
