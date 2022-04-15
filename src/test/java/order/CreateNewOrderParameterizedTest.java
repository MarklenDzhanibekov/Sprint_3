package order;

import data.GeneratedData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;
import org.junit.Test;
import requests.order.PostOrderRequest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



@RunWith(Parameterized.class)

public class CreateNewOrderParameterizedTest {

    private final OrderModel orderModel;

    public CreateNewOrderParameterizedTest(OrderModel orderModel) {
      this.orderModel = orderModel;
    }
    @Description("Подготовка данных для параметризованного теста")
    @Parameterized.Parameters (name = "{0} - передаем только один параметр, который является объектом класса Order.")
    public static Object[][] getNewOrderData() {
        return new Object[][] {
                {new GeneratedData().dataForOrderCreation(new String[] {"BLACK"})},
                {new GeneratedData().dataForOrderCreation(new String[] {"GRAY"})},
                {new GeneratedData().dataForOrderCreation(new String[] {"GRAY", "BLACK"})},
                {new GeneratedData().dataForOrderCreation(new String[] {""})},
        };
    }

    @Test
    @DisplayName("Параметризованный тест для создания нового заказа")
    public void testOrderCanBeCreated() {
        PostOrderRequest orderCanBeCreated = new PostOrderRequest();
        ValidatableResponse response = orderCanBeCreated.createTestNewOrderWithColor(orderModel);

        response.assertThat().statusCode(201).and().extract().path("track");
    }

}
