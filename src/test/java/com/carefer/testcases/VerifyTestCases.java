package com.carefer.testcases;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import modules.Verify;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class VerifyTestCases {

    RequestSpecification request;
    Header typeHeader1 = new Header("Platform","careferProviderApplication2Ej!%");
    Header typeHeader2 = new Header("Accept","application/json");
    Headers info = new Headers(typeHeader1,typeHeader2);
    String endpoint = "https://provider.test.carefer.co/api/provider/v1/auth/verify";

    @BeforeClass
    public void BeforeClass (){
        request  = given()
                .contentType(ContentType.JSON)
                .headers(info);


    }

    @Test(priority = 2)
    public void Ensure_User_Can_Verify_Account_Success(){

        File verifyInfo= new File("src/test/resources/Verify.json");

        given()
                .spec(request)
                .body(verifyInfo)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("status_code",equalTo(200))
                .body( "success",equalTo(true))
                .body("message",equalTo("Success verify."))
                .body("data.is_verified",equalTo(true));
    }

    @Test
    public void Ensure_User_Cannot_Verify_Account_with_wrong_OTB(){

        Verify verify  = new Verify("966556677839","9510");

        given()
                .spec(request)
                .body(verify)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("message",equalTo("خطأ فى بيانات الإدخال"))
                .body("errors",contains("err_auth_verify_verification_code_wrong"));

    }

    @Test
    public void Ensure_User_Cannot_Verify_Account_with_Empty_OTB(){

        Verify verify  = new Verify("966556677839","");

        given()
                .spec(request)
                .body(verify)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("message",equalTo("خطأ فى بيانات الإدخال"))
                .body("errors",contains("err_auth_verify_verification_code_mandatory"));

    }


    @Test
    public void Ensure_User_Cannot_Verify_Account_with_Empty_Mobile(){

        Verify verify  = new Verify("","9531");

        given()
                .spec(request)
                .body(verify)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("message",equalTo("خطأ فى بيانات الإدخال"))
                .body("errors",contains("err_auth_verify_mobile_mandatory"));

    }

}
