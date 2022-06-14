package oes.userservice.Models.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ModuleCreationDTO {
    private String modulename;
    private int semester;
}
