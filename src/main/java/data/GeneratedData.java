package data;
import io.qameta.allure.junit4.DisplayName;
import model.CourierCredentialsModel;
import model.OrderModelWithBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

public class GeneratedData {


  @DisplayName("Builder Метод для генерирование случайных данных для полей заказа")
  public OrderModelWithBuilder dataForOrderCreation() {

    OrderModelWithBuilder orderModelWithBuilder = new OrderModelWithBuilder.Builder()
            .withFirstName(RandomStringUtils.randomAlphabetic(10))
            .withLastName(RandomStringUtils.randomAlphabetic(10))
            .withAddress(RandomStringUtils.randomAlphabetic(10))
            .withMetroStation(RandomStringUtils.randomAlphabetic(10))
            .withPhone(RandomStringUtils.randomAlphabetic(10))
            .withRentTime(new Random().nextInt())
            .withDeliveryDate("2020-04-02")
            .withComment(RandomStringUtils.randomAlphabetic(10))
            .withColor(new String[]{"Black"})
            .build();

    return orderModelWithBuilder;
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