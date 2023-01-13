package com.carefer.testcases;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import modules.Login;
import modules.Register;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class LoginTestcases {

    RequestSpecification request;
    Header typeHeader1 = new Header("Platform","careferProviderApplication2Ej!%");
    Header typeHeader2 = new Header("Accept","application/json");
    Headers info = new Headers(typeHeader1,typeHeader2);
    String endpoint = "https://provider.test.carefer.co/api/provider/v1/auth/login";


    @BeforeClass
    public void BeforeClass (){
        request  = given()
                .contentType(ContentType.JSON)
                .headers(info);


    }
    @Test(priority = 3)
    public void User_Can_login_Success(){
        File authBody= new File("src/test/resources/login.json");

        given()
                .spec(request)
                .body(authBody)
         .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("status_code",equalTo(200))
                .body( "success",equalTo(true))
                .body("message",equalTo("Success Login."))
                .body("data.is_verified",equalTo(1));
    }

    @Test
    public void User_Cannot_login_with_un_Registered_mobile(){
        Login loginUnRegisteredMobile = new Login("966556677009","123456");


        given()
                .spec(request)
                .body(loginUnRegisteredMobile)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_login_mobile_not_exists"))
                .body("message",equalTo("خطأ فى بيانات الإدخال"));
    }

    @Test
    public void User_Cannot_login_with_Wrong_Password(){
        Login loginUnRegisteredMobile = new Login("966556677839","123459");

        given()
                .spec(request)
                .body(loginUnRegisteredMobile)
         .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(401)
                .assertThat().body("status_code",equalTo(401))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_login_password_wrong"))
                .body("message",equalTo("Wrong Password ."));
    }
    @Test
    public void User_Cannot_login_with_empty_mobile(){
        Login loginUnRegisteredMobile = new Login("","123456");

        given()
                .spec(request)
                .body(loginUnRegisteredMobile)
         .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_login_mobile_mandatory"))
                .body("message",equalTo("خطأ فى بيانات الإدخال"));
    }

    @Test
    public void User_Cannot_login_with_empty_Password(){
        Login loginUnRegisteredMobile = new Login("966556677839","");

        given()
                .spec(request)
                .body(loginUnRegisteredMobile)
        .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_login_password_mandatory"))
                .body("message",equalTo("خطأ فى بيانات الإدخال"));
    }
}


