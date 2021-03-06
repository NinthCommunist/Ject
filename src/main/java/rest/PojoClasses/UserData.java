package rest.PojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    int id;
    String email;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("avatar")
    String avatarUrl;
}
