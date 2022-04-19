package requests.order;
import io.qameta.allure.Step;
import requests.GeneralData;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class PutOrderRequest {

    String mainUrl = new GeneralData().getMAIN_URL();


    @Step("запрос для принятия заказа")
    public ValidatableResponse acceptOrder(int courierId, int orderId) {

        return given().header("Content-type", "application/json")
                .and().pathParam("orderId", orderId).pathParam("courierId", courierId)
                .when().put(mainUrl + "/api/v1/orders/accept/{orderId}?courierId={courierId}").then();

    }

    @Step("запрос для отмены заказа")
    public ValidatableResponse cancelOrder(int orderTrackNumber) {

        return given().header("Content-type", "application/json")
                .and().pathParam("orderTrackNumber", orderTrackNumber)
                .when().put(mainUrl + "/api/v1/orders/accept/{orderId}?courierId={courierId}").then();

    }
}
