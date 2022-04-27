package rest;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static config.Constants.URL;

public class RestSpecInstall {

   public static RequestSpecification createReqSpec(String path){
       return new RequestSpecBuilder()
               .setBaseUri(URL)
               .addFilter(new AllureRestAssured())
               .setBasePath(path)
               .build();
    }
    public static RequestSpecification createReqSpecParams(String path, Map<String, String> params ){
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBasePath(path)
                .setBaseUri(URL)
                .addQueryParams(params)
                .build();
    }

    public static RequestSpecification postReqSpec(String path, String jsonFileName){
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(URL)
                .setBasePath(path)
                .setBody(stringFromFileName(jsonFileName))
                .setContentType(ContentType.JSON)
                .build();
    }

    private static String stringFromFileName(String jsonFileName) {
        try{
            return (Files.readString(Paths.get("src/main/resources/properties/rest/json/" + jsonFileName + ".json")));

        } catch (IOException e) {
            e.printStackTrace();
            return "File is not exist";
        }
    }

    public static ResponseSpecification resSpec(Integer statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}