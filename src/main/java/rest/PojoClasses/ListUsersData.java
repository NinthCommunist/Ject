package rest.PojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUsersData {
    int pages;
    @JsonProperty("per_page")
    int perPage;
    @JsonProperty("data")
    List<UserData> userData;
}
