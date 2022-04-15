package requests.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class GetOrderRequest {
    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";

    //запрос для получения ID номера заказа

    public ValidatableResponse getOrderById(int orderTackNumber) {
         return given().header("Content-type", "application/json")
                .and().pathParam("orderTrackNumber", orderTackNumber)
                .when().get(MAIN_URL + "/api/v1/orders/track?t={orderTrackNumber}").then();
    }

    public ValidatableResponse getOrdersListByCourierId(int courierId) {
        return given().header("Content-type", "application/json")
                .and().pathParam("courierId", courierId)
                .when().get(MAIN_URL + "/api/v1/orders?courierId={courierId}").then();
    }
}
