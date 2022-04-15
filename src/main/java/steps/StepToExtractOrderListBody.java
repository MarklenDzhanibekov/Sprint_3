package steps;

import io.qameta.allure.Step;
import model.orders.list.OrdersListModel;

import static io.restassured.RestAssured.given;

public class StepToExtractOrderListBody {

    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";

    @Step("Шаг для извлечения body списка заказов")
    public OrdersListModel extractOrderListBody (int courierId) {

        OrdersListModel extractedOrder = given()
        .header("Content-type", "application/json")
                .pathParam("courierId", courierId)
                .get( MAIN_URL + "/api/v1/orders?courierId={courierId}")
                .body().as(OrdersListModel.class);
        return extractedOrder;
    }
}
