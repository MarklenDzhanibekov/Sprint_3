package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


public class StepToGetOrderTrackNumber {
    private int trackId;

    @Step("Шаг для получения трэк номера заказа")
    public int getOrderTrackNumber(ValidatableResponse response) {

        if (response.extract().statusCode() == 201) {
            trackId = response.assertThat().extract().path("track");
        } else {
            System.out.println("Track ID заказа не может быть извлечен");
        }
        return trackId;
    }

}
