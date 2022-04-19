package requests.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierCredentialsModel;
import requests.GeneralData;

import static io.restassured.RestAssured.given;


public class PostCourierRequest {
    String mainUrl = new GeneralData().getMAIN_URL();

    @Step("запрос для создания нового курьера c firstName")
    public ValidatableResponse createNewCourier(String login, String password, String firstName) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password, firstName);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(mainUrl + "/api/v1/courier/").then();
    }

    @Step("запрос для создания нового курьера БЕЗ firstName")
    public ValidatableResponse createNewCourierWithoutFirstName (String login, String password) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(mainUrl + "/api/v1/courier/").then();

    }

    @Step("запрос для создания нового курьера с использованием сгенерированных данных")
    public ValidatableResponse createNewCourierWithGeneratedData (CourierCredentialsModel generatedCourierCredentials) {


        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(mainUrl + "/api/v1/courier/").then();
    }


    @Step("запрос авторизации курьера при помощи логина и пароля")
    public ValidatableResponse loginCourier(String login, String password) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(mainUrl + "/api/v1/courier/login").then();
    }

    @Step("запрос авторизации курьера при помощи объекьта с сгенерированными данными")
    public ValidatableResponse loginCourier(CourierCredentialsModel generatedCourierCredentials) {

        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(mainUrl + "/api/v1/courier/login").then();
    }

    @Step("получение Id курьер при помощи логина и пароля")
    public int getCourierId(String login, String password) {

            CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

            return given().header("Content-type", "application/json")
                    .and().body(newCredentialsForCourier)
                    .when().post(mainUrl + "/api/v1/courier/login").then().extract().path("id");
    }

    @Step("получение Id курьер при помощи объекьта с сгенерированными данными")
    public int getCourierId(CourierCredentialsModel generatedCourierCredentials) {

        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(mainUrl + "/api/v1/courier/login").then().extract().path("id");
    }

}
