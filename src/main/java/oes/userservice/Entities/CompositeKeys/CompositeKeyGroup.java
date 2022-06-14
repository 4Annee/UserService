package oes.userservice.Entities.CompositeKeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class CompositeKeyGroup implements Serializable {
    private int year;
    private String section;
    private int group;
}
