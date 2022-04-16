package requests.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import requests.GeneralData;

import static io.restassured.RestAssured.given;

public class DeleteCourierRequest {
    String mainUrl = new GeneralData().getMAIN_URL();


    @Step("Запрос для удаления курьера с использованием Id курьера")
    public ValidatableResponse  deleteCourierByID(int id) {

        return given().header("Content-type", "application/json")
                .and().pathParam("id", id)
                .when().delete(mainUrl + "/api/v1/courier/{id}").then();
    }

}
