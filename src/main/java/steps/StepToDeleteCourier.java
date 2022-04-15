package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import requests.courier.DeleteCourierRequest;
import requests.courier.PostCourierRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class StepToDeleteCourier {

    @Step("Шаг для удаления курьера с помощью логина и пароля курьера")
    public void deletionCourier(String login, String password) {
        PostCourierRequest postCourierRequest = new PostCourierRequest();
        DeleteCourierRequest deleteCourierRequest = new DeleteCourierRequest();
        ValidatableResponse response = postCourierRequest.loginCourier(login, password);

        if (response.extract().statusCode() == 200) {

            int currentCourierId = postCourierRequest.getCourierId(login, password);
            int actualStatusCode = deleteCourierRequest.deleteCourierByID(currentCourierId).extract().statusCode();
            assertThat("Курьер не найден и не может быть удален.", 200, equalTo(actualStatusCode));
        }
    }

    @Step("Шаг для удаления курьера через Id номер курьера")
    public void deletionCourier(int courierId) {

        DeleteCourierRequest deleteCourierRequest = new DeleteCourierRequest();
        deleteCourierRequest.deleteCourierByID(courierId);

    }
}

