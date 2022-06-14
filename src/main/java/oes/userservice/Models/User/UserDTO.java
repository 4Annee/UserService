package oes.userservice.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oes.userservice.Entities.Enums.Role;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private String userid;
    private Role role;
    private int year;
    private String Section;
    private int group;
}
