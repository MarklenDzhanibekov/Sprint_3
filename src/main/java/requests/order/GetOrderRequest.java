package requests.order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import requests.GeneralData;


import static io.restassured.RestAssured.given;

public class GetOrderRequest {
    String mainUrl = new GeneralData().getMAIN_URL();

    @Step("запрос для получения ID номера заказа")
    public ValidatableResponse getOrderById(int orderTackNumber) {
         return given().header("Content-type", "application/json")
                .and().pathParam("orderTrackNumber", orderTackNumber)
                .when().get(mainUrl + "/api/v1/orders/track?t={orderTrackNumber}").then();
    }

}
