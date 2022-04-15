package requests.order;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class PutOrderRequest {
    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";

    //Запрос для принятия заказа
    public ValidatableResponse acceptOrder(int courierId, int orderId) {

        return given().header("Content-type", "application/json")
                .and().pathParam("orderId", orderId).pathParam("courierId", courierId)
                .when().put(MAIN_URL + "/api/v1/orders/accept/{orderId}?courierId={courierId}").then();

    }

    //Запрос на отмену заказа
    public ValidatableResponse cancelOrder(int orderTrackNumber) {

        return given().header("Content-type", "application/json")
                .and().pathParam("orderTrackNumber", orderTrackNumber)
                .when().put(MAIN_URL + "/api/v1/orders/accept/{orderId}?courierId={courierId}").then();

    }
}
