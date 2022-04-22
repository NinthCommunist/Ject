package rest;

import org.testng.annotations.Test;
import rest.PojoClasses.CUDUserData;
import rest.PojoClasses.ListUsersData;
import rest.steps.UserStep;

public class UsersTest {
    UserStep userStep = new UserStep();
    @Test
    public void usersListTest(){
        ListUsersData listUsersData = userStep.getUsersList("2");
        System.out.println(listUsersData.getUserData().get(2).getFirstName());
    }

    @Test
    public void createUser(){
        CUDUserData cudUser = userStep.createUser("createUser", 201);
        System.out.println(cudUser.getName());
    }


}
