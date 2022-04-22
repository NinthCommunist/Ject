package rest.steps;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import rest.PojoClasses.CUDUserData;
import rest.PojoClasses.ListUsersData;
import rest.PojoClasses.UserData;
import rest.RestSpecInstall;

import java.util.Map;

public class UserStep {
    private String path = "/api/users";
    private RequestSpecification REQ_GET_SPEC = RestSpecInstall.createReqSpec(path);

    public ListUsersData getUsersList(String page, int statusCode){
        return RestAssured.given(RestSpecInstall.createReqSpecParams(path, Map.of("page", page)), RestSpecInstall.resSpec(statusCode))
                .get().then().log().all()
                .extract().body().as(ListUsersData.class);
    }
    public ListUsersData getUsersList(String page){
        return getUsersList(page, 200);
    }

    public UserData getUserFromId(String userId, int statusCode){
        return RestAssured.given(REQ_GET_SPEC, RestSpecInstall.resSpec(statusCode))
                .get(userId).then().log().all()
                .extract().body().
                jsonPath().getObject("data", UserData.class);
    }

    public UserData getUserFromId(String userId){
        return getUserFromId(userId, 200);
    }

    public CUDUserData createUser(String jsonName, Integer statusCode){
        return RestAssured.given(RestSpecInstall.postReqSpec(path, jsonName), RestSpecInstall.resSpec(statusCode))
                .post().then().log().all().extract().body().as(CUDUserData.class);
    }


}
