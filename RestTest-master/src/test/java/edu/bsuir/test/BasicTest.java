package edu.bsuir.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class BasicTest {

    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/SpringBootRestApi/api/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    protected String createResource(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().header("location");
    }


    protected <T> List<T> getListOfAllResources(String path, Class<T> responseClass) {
        return given()
                .spec(spec)
                .get(path)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".",responseClass);
    }

    protected int getStatusCodeOfGETRequestToAllResources(String path) {
        return given()
                .spec(spec)
                .get(path)
                .then()
                .extract().statusCode();
    }

    protected <T> T getResource(String locationHeader, Class<T> responseClass) {
        return given()
                .spec(spec)
                .when()
                .get(locationHeader)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

    protected int delete(String path) {
        return given()
                .spec(spec)
                .when()
                .delete(path)
                .then()
                .extract().statusCode();
    }

    protected int put(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .put(path)
                .then()
                .extract().statusCode();
    }


}
