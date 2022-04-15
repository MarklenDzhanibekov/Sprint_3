package requests.order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class PostOrderRequest {

    private final String MAIN_URL = "https://qa-scooter.praktikum-services.ru";


    //запрос для создания нового заказа с цветом
    public ValidatableResponse createNewOrderWithColor(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {

        OrderModel orderModelData = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        return given().header("Content-type", "application/json")
                .and().body(orderModelData)
                .when().post(MAIN_URL + "/api/v1/orders").then();
    }

    //Запрос для создания нового заказа с цветом
    public ValidatableResponse createTestNewOrderWithColor(OrderModel orderModel) {

        return given().header("Content-type", "application/json")
                .and().body(orderModel)
                .when().post(MAIN_URL + "/api/v1/orders").then();
    }


    //запрос для создания нового заказа без цвета
    public ValidatableResponse createNewOrderWithoutColor(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] strings) {

        OrderModel orderModelData = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);

        //смотрим что отправляется в json файле
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json;
        System.out.println(json = gson.toJson(orderModelData));

        return given().header("Content-type", "application/json")
                .and().body(orderModelData)
                .when().post(MAIN_URL + "/api/v1/orders").then();

    }
}
