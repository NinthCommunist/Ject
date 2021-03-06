package rest.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import rest.PojoClasses.CUDUserData;
import rest.PojoClasses.CreateUserData;
import rest.PojoClasses.ListUsersData;
import rest.PojoClasses.UserData;
import rest.RestSpecInstall;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Map;

public class UserStep {
    private String path = "/api/users";
    private RequestSpecification REQ_GET_SPEC = RestSpecInstall.createReqSpec(path);

    @Step(value = "Получение списка пользователей")
    public ListUsersData getUsersList(String page, int statusCode) {
        return RestAssured.given(RestSpecInstall.createReqSpecParams(path, Map.of("page", page)), RestSpecInstall.resSpec(statusCode))
                .get().then().log().all()
                .extract().body().as(ListUsersData.class);
    }

    @Step(value = "Получение списка пользователей")
    public ListUsersData getUsersList(String page) {
        return getUsersList(page, 200);
    }


    public UserData getUserFromId(String userId, int statusCode) {
        return RestAssured.given(REQ_GET_SPEC, RestSpecInstall.resSpec(statusCode))
                .get(userId).then().log().all()
                .extract().body().
                jsonPath().getObject("data", UserData.class);
    }

    public UserData getUserFromId(String userId) {
        return getUserFromId(userId, 200);
    }

    @Step(value = "Создание пользователя через json файл")
    public CUDUserData createUserWithJsonFile(String jsonName, Integer statusCode) {
        return RestAssured.given(RestSpecInstall.postReqSpec(path, jsonName), RestSpecInstall.resSpec(statusCode))
                .post().then().log().all().extract().body().as(CUDUserData.class);
    }

    @Step(value = "Создание пользователя через POJO")
    public CUDUserData createUserWithPojo(CreateUserData user, Integer statusCode) {
        return RestAssured.given(RestSpecInstall.createReqSpec(path).body(user).log().all(), RestSpecInstall.resSpec(statusCode))
                .post().then().log().all().extract().body().as(CUDUserData.class);
    }
}