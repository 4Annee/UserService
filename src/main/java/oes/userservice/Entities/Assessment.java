package oes.userservice.Entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Assessment {
    private String assessmentTitle;
    private String moduleId;
    private Date startTime;
    private Date endTime;
}
