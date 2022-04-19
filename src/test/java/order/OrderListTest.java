package order;

import data.GeneratedData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierCredentialsModel;
import model.OrderModelWithBuilder;
import model.orders.list.OrdersListModel;
import org.junit.After;
import org.junit.Test;
import requests.courier.PostCourierRequest;
import requests.order.GetOrderRequest;
import requests.order.PostOrderRequest;
import requests.order.PutOrderRequest;
import steps.StepToDeleteCourier;
import steps.StepToExtractOrderListBody;
import steps.StepToGetOrderTrackNumberAndId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class OrderListTest {

    //Сгенерировали рандомные данные для заказа и для курьера
    GeneratedData generatedData = new GeneratedData();
    CourierCredentialsModel courierCredentials = generatedData.dataForCourier();
    OrderModelWithBuilder orderData = generatedData.dataForOrderCreation();

    //Создаем курьера и заказ
    PostCourierRequest postCourierRequest = new PostCourierRequest();
    ValidatableResponse courierResponse = postCourierRequest.createNewCourierWithGeneratedData(courierCredentials);
    PostOrderRequest postOrderRequest = new PostOrderRequest();
    ValidatableResponse orderResponse = postOrderRequest.createNewOrder((orderData));

    // получили ID курьера
    int courierId = postCourierRequest.getCourierId(courierCredentials);

    // получили Track номер заказа
    StepToGetOrderTrackNumberAndId stepToGetOrderTrackNumberAndId = new StepToGetOrderTrackNumberAndId();
    int orderTrackNumber = stepToGetOrderTrackNumberAndId.getOrderTrackNumber(orderResponse);

    //получили ID номер заказа
    GetOrderRequest getOrderRequest = new GetOrderRequest();
    ValidatableResponse responseTrack = getOrderRequest.getOrderById(orderTrackNumber);
    int orderIdNumber = stepToGetOrderTrackNumberAndId.getOrderIdNumber(responseTrack);

    @After
    @DisplayName("Удаление курьера через аннотацию @After")
    public void courierDeletionAfterTests () {
        StepToDeleteCourier stepToDeleteCourier = new StepToDeleteCourier();
        stepToDeleteCourier.deletionCourier(courierId);
    }

    @Test
    @DisplayName("Проверяем список заказов по идентификатору курьера")
    public void acceptOrderTest () {

        //принимаем заказ
        PutOrderRequest putOrderRequest = new PutOrderRequest();
        ValidatableResponse result  = putOrderRequest.acceptOrder(courierId, orderIdNumber);

        //извлекаем тело списка заказов
        StepToExtractOrderListBody stepToExtractOrderListBody = new StepToExtractOrderListBody();
        OrdersListModel extractedOrder = stepToExtractOrderListBody.extractOrderListBody(courierId);

        //убеждаемся что заказ был принят успешно
        result.assertThat().body("ok", equalTo(true)).and().statusCode(200);

        //сравниваем данные заказа из полученного списка заказов с исходными данными
        assertThat("Поле firstName не совпадает", extractedOrder.getOrders().get(0).getFirstName(), equalTo(orderData.getFirstName()));
        assertThat("Поле lastName не совпадает", extractedOrder.getOrders().get(0).getLastName(), equalTo(orderData.getLastName()));
        assertThat("Поле address не совпадает", extractedOrder.getOrders().get(0).getAddress(), equalTo(orderData.getAddress()));
        assertThat("Поле metroStation не совпадает", extractedOrder.getOrders().get(0).getMetroStation(), equalTo(orderData.getMetroStation()));
        assertThat("Поле phone не совпадает", extractedOrder.getOrders().get(0).getPhone(), equalTo(orderData.getPhone()));
        assertThat("Поле rentTime не совпадает", extractedOrder.getOrders().get(0).getRentTime(), equalTo(orderData.getRentTime()));
        assertThat("Поле comment не совпадает", extractedOrder.getOrders().get(0).getComment(), equalTo(orderData.getComment()));
        assertThat("Поле color не совпадает", extractedOrder.getOrders().get(0).getColor(), equalTo(orderData.getColor()));

    }



}
