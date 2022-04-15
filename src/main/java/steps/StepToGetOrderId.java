package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class StepToGetOrderId {

    private int orderIdNumber;

    @Step("Шаг для получения Id заказа")
    public int getOrderIdNumber(ValidatableResponse response) {

        if (response.extract().statusCode() == 200) {
            orderIdNumber = response.assertThat().extract().path("order.id");
        } else {
            System.out.println("Id номер заказа не может быть извлечен");
        }
        return orderIdNumber;

    }
}
