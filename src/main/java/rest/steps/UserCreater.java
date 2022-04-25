package rest.steps;

import rest.PojoClasses.CreateUserData;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class UserCreater {
    public static CreateUserData getRandomCreateUser(){
        return new PodamFactoryImpl().manufacturePojo(CreateUserData.class);
    }
}
