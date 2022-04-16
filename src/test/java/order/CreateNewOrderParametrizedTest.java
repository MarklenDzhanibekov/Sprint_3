package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderModelWithBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import requests.order.PostOrderRequest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;


@RunWith(Parameterized.class)

public class CreateNewOrderParametrizedTest {

    private final OrderModelWithBuilder orderModelWithBuilder;

    public CreateNewOrderParametrizedTest(OrderModelWithBuilder orderModelWithBuilder) {
        this.orderModelWithBuilder = orderModelWithBuilder;
    }

    @Description("Подготовка данных для параметризованного теста")
    @Parameterized.Parameters (name = "{index} - передаем только один параметр, который является объектом класса Order.")
    public static Object[][] getNewOrderData() {
        return new Object[][] {

                {new OrderModelWithBuilder.Builder()
                        .withFirstName(RandomStringUtils.randomAlphabetic(10))
                        .withLastName(RandomStringUtils.randomAlphabetic(10))
                        .withAddress(RandomStringUtils.randomAlphabetic(10))
                        .withMetroStation(RandomStringUtils.randomAlphabetic(10))
                        .withPhone(RandomStringUtils.randomAlphabetic(10))
                        .withRentTime(new Random().nextInt())
                        .withComment(RandomStringUtils.randomAlphabetic(10))
                        .withColor(new String[]{""})
                        .build()},

                {new OrderModelWithBuilder.Builder()
                        .withFirstName(RandomStringUtils.randomAlphabetic(10))
                        .withLastName(RandomStringUtils.randomAlphabetic(10))
                        .withAddress(RandomStringUtils.randomAlphabetic(10))
                        .withMetroStation(RandomStringUtils.randomAlphabetic(10))
                        .withPhone(RandomStringUtils.randomAlphabetic(10))
                        .withRentTime(new Random().nextInt())
                        .withComment(RandomStringUtils.randomAlphabetic(10))
                        .withColor(new String[]{"BLACK"})
                        .build()},

                {new OrderModelWithBuilder.Builder()
                        .withFirstName(RandomStringUtils.randomAlphabetic(10))
                        .withLastName(RandomStringUtils.randomAlphabetic(10))
                        .withAddress(RandomStringUtils.randomAlphabetic(10))
                        .withMetroStation(RandomStringUtils.randomAlphabetic(10))
                        .withPhone(RandomStringUtils.randomAlphabetic(10))
                        .withRentTime(new Random().nextInt())
                        .withDeliveryDate("2020-04-02")
                        .withComment(RandomStringUtils.randomAlphabetic(10))
                        .withColor(new String[]{"BLACK, GRAY"})
                        .build()},

                {new OrderModelWithBuilder.Builder()
                        .withFirstName(RandomStringUtils.randomAlphabetic(10))
                        .withLastName(RandomStringUtils.randomAlphabetic(10))
                        .withAddress(RandomStringUtils.randomAlphabetic(10))
                        .withMetroStation(RandomStringUtils.randomAlphabetic(10))
                        .withPhone(RandomStringUtils.randomAlphabetic(10))
                        .withRentTime(new Random().nextInt())
                        .withDeliveryDate("2020-04-02")
                        .withComment(RandomStringUtils.randomAlphabetic(10))
                        .withColor(new String[]{"GRAY"})
                        .build()}
        };
    }

    @Test
    @DisplayName("Параметризованный тест для создания нового заказа")
    public void testOrderCanBeCreated() {
        PostOrderRequest orderCanBeCreated = new PostOrderRequest();
        ValidatableResponse response = orderCanBeCreated.createNewOrder(orderModelWithBuilder);
        response.assertThat().statusCode(201).and().extract().path("track");
    }

}
