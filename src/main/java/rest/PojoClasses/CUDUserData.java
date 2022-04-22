package rest.PojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CUDUserData {
    String name;
    String job;
    String id;
    String createdAt;
    String updatedAt;
}
