package com.req.res.testbase;

/*
Created by SP
*/

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {


    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
