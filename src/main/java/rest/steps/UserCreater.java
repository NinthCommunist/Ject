package rest.steps;

import io.qameta.allure.Step;
import rest.PojoClasses.CreateUserData;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class UserCreater {
    @Step(value = "Создание случайного юзера")
    public static CreateUserData getRandomCreateUser(){
        return new PodamFactoryImpl().manufacturePojo(CreateUserData.class);
    }
}
