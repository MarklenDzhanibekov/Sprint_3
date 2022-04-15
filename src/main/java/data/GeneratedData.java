package data;
import io.qameta.allure.junit4.DisplayName;
import model.CourierCredentialsModel;
import model.OrderModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Random;

public class GeneratedData {

  @DisplayName("Метод для генерирование случайных данных для полей заказа")
  public OrderModel dataForOrderCreation(String[] color) {

    String firstName = RandomStringUtils.randomAlphabetic(10);
    String lastName = RandomStringUtils.randomAlphabetic(10);
    String address = RandomStringUtils.randomAlphabetic(10);
    String metroStation = RandomStringUtils.randomAlphabetic(10);
    String phone = RandomStringUtils.randomAlphabetic(10);
    int rentTime = new Random().nextInt();
    String deliveryDate = String.valueOf(LocalDateTime.now().toLocalDate());
    String comment = RandomStringUtils.randomAlphabetic(10);

    OrderModel ordersData = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    return ordersData;
  }


  @DisplayName("Метод для генерирование случайных данных для полей курьера")
  public CourierCredentialsModel dataForCourier() {

    String login = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);
    String firstName = RandomStringUtils.randomAlphabetic(10);

    CourierCredentialsModel courierData = new CourierCredentialsModel(login, password, firstName);
    return courierData;
  }


}