package rest.PojoClasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStringValue;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CreateUserData {
    @PodamStringValue(length = 5)
    private String name;
    @PodamStringValue(length = 5)
    private String job;
}
