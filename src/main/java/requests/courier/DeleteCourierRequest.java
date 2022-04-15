package requests.courier;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteCourierRequest {
    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";

    //удаление курьера через id
    public ValidatableResponse  deleteCourierByID(int id) {

        return given().header("Content-type", "application/json")
                .and().pathParam("id", id)
                .when().delete(MAIN_URL + "/api/v1/courier/{id}").then();
    }

}
