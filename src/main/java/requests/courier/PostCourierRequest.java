package requests.courier;

import io.restassured.response.ValidatableResponse;
import model.CourierCredentialsModel;
import static io.restassured.RestAssured.given;


public class PostCourierRequest {
    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";

    //запрос для создания нового курьера c firstName
    public ValidatableResponse createNewCourier(String login, String password, String firstName) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password, firstName);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(MAIN_URL + "/api/v1/courier/").then();
    }

    //запрос для создания нового курьера БЕЗ firstName
    public ValidatableResponse createNewCourierWithoutFirstName (String login, String password) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(MAIN_URL + "/api/v1/courier/").then();

    }

    public ValidatableResponse createNewCourierWithGeneratedData (CourierCredentialsModel generatedCourierCredentials) {


        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(MAIN_URL + "/api/v1/courier/").then();
    }


    // запрос для логина
    public ValidatableResponse loginCourier(String login, String password) {

        CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

        return given().header("Content-type", "application/json")
                .and().body(newCredentialsForCourier)
                .when().post(MAIN_URL + "/api/v1/courier/login").then();
    }

    public ValidatableResponse loginCourier(CourierCredentialsModel generatedCourierCredentials) {

        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(MAIN_URL + "/api/v1/courier/login").then();
    }

    //получение ID курьера
    public int getCourierId(String login, String password) {

            CourierCredentialsModel newCredentialsForCourier = new CourierCredentialsModel(login, password);

            return given().header("Content-type", "application/json")
                    .and().body(newCredentialsForCourier)
                    .when().post(MAIN_URL + "/api/v1/courier/login").then().extract().path("id");
    }

    public int getCourierId(CourierCredentialsModel generatedCourierCredentials) {

        return given().header("Content-type", "application/json")
                .and().body(generatedCourierCredentials)
                .when().post(MAIN_URL + "/api/v1/courier/login").then().extract().path("id");
    }

}
