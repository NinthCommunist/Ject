package rest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rest.PojoClasses.CUDUserData;
import rest.PojoClasses.CreateUserData;
import rest.PojoClasses.ListUsersData;
import rest.PojoClasses.UserData;
import rest.steps.UserCreater;
import rest.steps.UserStep;

import java.util.List;

public class UsersTest {
    UserStep userStep = new UserStep();

    @BeforeClass
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void usersListTest() {
        ListUsersData listUsersData = userStep.getUsersList("2");
        Assert.assertTrue(listUsersData.getUserData().size() == listUsersData.getPerPage());
    }

    @Test
    public void comparisonAvatarAndIdForEvenUser() {
        ListUsersData listUsersData = userStep.getUsersList("2");
        List<UserData> users = listUsersData.getUserData();
        List<UserData> evenUsers = users.stream().filter(x -> x.getId() % 2 == 0).toList();
        evenUsers.forEach(x->Assert.assertTrue(x.getAvatarUrl().contains(String.valueOf(x.getId()))));
    }

    @Test
    public void createUserWithJsonFile() {
        CUDUserData cudUser = userStep.createUserWithJsonFile("createUser", 201);
        System.out.println(cudUser.getName());
    }

    @Test
    public void createUserWithRandomUser() {
        CreateUserData createUser = UserCreater.getRandomCreateUser();
        userStep.createUserWithPojo(createUser, 201);
    }

    @Test
    public void createUserWithUserBuilder() {
        CreateUserData createUser = CreateUserData.builder()
                .name("Steve")
                .job("Buscemi")
                .build();
        userStep.createUserWithPojo(createUser, 201);
    }

}