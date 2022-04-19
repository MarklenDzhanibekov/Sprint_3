package steps;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import requests.courier.PostCourierRequest;


public class StepToCreateCourier {

    @Step("Шаг для создание курьера")
    public void creationCourier(String login, String password) {
        PostCourierRequest postCourierRequest = new PostCourierRequest();
        ValidatableResponse response = postCourierRequest.createNewCourierWithoutFirstName(login, password);
        response.assertThat().statusCode(201);
    }
}
