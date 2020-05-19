package com.req.res.reqresuserinfo;

/*
Created by SP
*/

import com.req.res.model.ReqresPojo;
import com.req.res.testbase.TestBase;
import com.req.res.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ReqresCRUDTest extends TestBase {

    static String name;
    static String job;

    @Title("Create an User Successfully and verify name added")
    @Test
    public void test001() {

        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setName(name = "Roger");
        reqresPojo.setJob(job = "Acting");

        SerenityRest.rest().given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(reqresPojo)
                .post("/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name", equalTo(name));

//                .extract().path("findAll{it.name=='" + name + "'}.get(0)");
//        assertThat(value, equalTo(name));
//        nameID = (int) value.get("id");
//        System.out.println("The user ID for the new student is : " + nameID);

    }

    @Title("Getting User from the record")
    @Test
    public void test002() {

        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setName(name = "Michael");

        SerenityRest.rest()
                .header("Content-Type", "application/json")
                .given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data.first_name", hasItems(name));

    }

    @Title("This test will update details of an user with ID5")
    @Test
    public void test003() {
        ReqresPojo reqResPojo = new ReqresPojo();
        reqResPojo.setName(name = "Johny");
        reqResPojo.setJob(job = "Golf");

        SerenityRest.rest().given()
                .log().all()
                .header("Content-Type", "application/json")
                .when().log().body()
                .body(reqResPojo)
                .put("/5")
                .then()
                .statusCode(200).log().body()
                .body("name", equalTo(name),
                        "job", equalTo(job));

    }

    @Title("This test will delete an User")
    @Test
    public void test004() {
        SerenityRest.rest().given()
                .when()
                .delete("/users/2")
                .then().statusCode(204)
                .log().status()
                .log().ifValidationFails();

    }

}
