package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import requests.courier.PostCourierRequest;
import steps.StepToDeleteCourier;

import static org.hamcrest.Matchers.equalTo;

public class CreateNewCourierTest {

    @After
    @DisplayName("After метод, который удаляет курьера после выполнения каждого теста")
    public void cleanUpCouriersData () {
        StepToDeleteCourier stepToDeleteCourier = new StepToDeleteCourier();
            stepToDeleteCourier.deletionCourier(login, password);
    };

    private String login = RandomStringUtils.randomAlphabetic(10);
    private String password = RandomStringUtils.randomAlphabetic(10);
    private String firstName = RandomStringUtils.randomAlphabetic(10);


    //Курьера можно создать
    @Test
    @DisplayName("Создание курьера")
    public void testCourierCanBeCreated () {

        PostCourierRequest postCourierCourierCanBeCreatedRequest = new PostCourierRequest();
        ValidatableResponse response = postCourierCourierCanBeCreatedRequest.createNewCourier(login, password, firstName);
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);

    }

    //нельзя создать двух одинаковых курьеров;
    @Test
    @DisplayName("Два курьера с одинаковыми данными не могут быть созданны")
    public void testTwoIdenticalCouriersCanNotBeCreated () {

        PostCourierRequest postCourierRequestTwoIdenticalCouriersCanNotBeCreated = new PostCourierRequest();
        ValidatableResponse response = postCourierRequestTwoIdenticalCouriersCanNotBeCreated.createNewCourier(login, password, firstName);
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);

        response = postCourierRequestTwoIdenticalCouriersCanNotBeCreated.createNewCourier(login, password, firstName);
        response.assertThat().body("message", equalTo("Этот логин уже используется")).and().statusCode(409);
    }

    @Test
    @DisplayName("Невозможно создать курьера без логина")
    public  void testCreateCourierWithoutLogin() {

        PostCourierRequest postCourierCreateCourierWithoutLoginRequest = new PostCourierRequest();
        ValidatableResponse response = postCourierCreateCourierWithoutLoginRequest.createNewCourier("", password, firstName);
        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);

        }

    @Test
    @DisplayName("Невозможно создать курьера без пароля")
    public  void testCreateCourierWithoutPassword() {

        PostCourierRequest createCourierWithoutPassword = new PostCourierRequest();
        ValidatableResponse response = createCourierWithoutPassword.createNewCourier(login, "", firstName);
        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Test
    @DisplayName("Невозможно создать курьера с уже существующим логином")
    public void testCouriersWithIdenticalLoginCanNotBeCreated () {

        PostCourierRequest postCourierRequestCouriersWithIdenticalLoginCanNotBeCreated = new PostCourierRequest();
        ValidatableResponse response = postCourierRequestCouriersWithIdenticalLoginCanNotBeCreated.createNewCourier(login, "testPW1", firstName);
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);

        response = postCourierRequestCouriersWithIdenticalLoginCanNotBeCreated.createNewCourier(login, "testPW1", firstName);
        response.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой.")).and().statusCode(409);
    }

}




