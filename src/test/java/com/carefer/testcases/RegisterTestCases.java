package com.carefer.testcases;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modules.Register;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTestCases {

    RequestSpecification request;
    Header typeHeader1 = new Header("Platform","careferProviderApplication2Ej!%");
    Header typeHeader2 = new Header("Accept","application/json");
    Headers info = new Headers(typeHeader1,typeHeader2);
    String endpoint = "https://provider.test.carefer.co/api/provider/v1/auth/register";


    @BeforeClass
    public void BeforeClass (){
        request  = given()
                .contentType(ContentType.JSON)
                .headers(info);


    }


@Test(priority = 1)
    public void User_Can_Register_Success(){
    File registerInfo= new File("src/test/resources/Register.json");


       given()
                .spec(request)
               .body(registerInfo)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("status_code",equalTo(200))
                .body( "success",equalTo(true))
                .body("message",equalTo("Success Register."));
    }


    @Test
    public void User_Can_Register_Success_Without_optional_Param(){

        Register register = new Register("123456","123456","",
                "Ahmed","abdoi",true,"9665568776651","ali@gmail.com");

        given()
                .spec(request)
                .body(register)
         .when()
                .post(endpoint)

                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("status_code",equalTo(200))
                .body( "success",equalTo(true))
                .body("message",equalTo("Success Register."));
    }


    @Test
    public void User_Cannot_Register_With_MobileNumber_Registered(){

        Register register = new Register("123456","123456","en",
                "Ahmed","abdoi",true,"966556877111","ali@gmail.com");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

         .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_mobile_already_exists"));
    }

    @Test
    public void Ensure_MobileNumber_Is_Mandatory(){

        Register register = new Register("123456","123456","en",
                "Ahmed","abdoi",true,"","ali@gmail.com");

        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_mobile_mandatory"));
    }
    @Test
    public void Ensure_Password_Is_Mandatory(){

        Register register = new Register("","123456","en",
                "Ahmed","abdoi",true,"966586877111","ali@gmail.com");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_password_mandatory","err_auth_register_password_confirmation_mismatch"));
    }
    @Test
    public void Ensure_confirmationPassword_Is_Mandatory(){

        Register register = new Register("3242146","","en",
                "Ahmed","abdoi",true,"966586877111","ali@gmail.com");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("الحقل تأكيد كلمة المرور إذا توفّر كلمة المرور."));
    }

    @Test
    public void Ensure_is_accept_terms_and_conditions_Is_Mandatory(){

        Register register = new Register("123456","123456","en",
                "Ahmed","abdoi",false,"966586877111","ali@gmail.com");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_is_accept_terms_and_conditions_should_be_accepted"));
    }

    @Test
    public void Ensure_Email_Is_Mandatory(){

        Register register = new Register("123456","123456","en",
                "Ahmed","abdoi",true,"966586877111","");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_email_format"));
    }
    @Test
    public void Ensure_responsible_person_Is_Mandatory(){

        Register register = new Register("123456","123456","en",
                "","abdoi",true,"966586877111","aaa@gmail.com");


        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_responsible_person_mandatory"));
    }
    @Test
    public void Ensure_name_Is_Mandatory(){

        Register register = new Register("123456","123456","en",
                "ahmed","",true,"966586877111","aaa@gmail.com");



        given()
                .spec(request)
                .body(register)
        .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_name_mandatory"));
    }

    @Test
    public void Ensure_MobileNumber_Is_SaudiFormat(){

        Register register = new Register("123456","123456","en",
                "ahmed","rrtfeee",true,"444586877111","aaa@gmail.com");



        given()
                .spec(request)
                .body(register)
         .when()
                .post(endpoint)

        .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("status_code",equalTo(422))
                .body( "success",equalTo(false))
                .body("errors",contains("err_auth_register_mobile_format"));
    }

}
