package requests.order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderModelWithBuilder;
import requests.GeneralData;


import static io.restassured.RestAssured.given;

public class PostOrderRequest {

    String mainUrl = new GeneralData().getMAIN_URL();

    @Step("запрос для создания нового заказа")
    public ValidatableResponse createNewOrder(OrderModelWithBuilder orderModelWithBuilder) {

        return given().header("Content-type", "application/json")
                .and().body(orderModelWithBuilder)
                .when().post(mainUrl + "/api/v1/orders").then();

    }

}
