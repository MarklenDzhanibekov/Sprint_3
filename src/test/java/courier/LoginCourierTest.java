package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import requests.courier.PostCourierRequest;
import steps.StepToCreateCourier;
import steps.StepToDeleteCourier;
import static org.hamcrest.Matchers.equalTo;



public class LoginCourierTest {

    //подготовили логин и пароль для некоторых тестов
    String wrongPassword = RandomStringUtils.randomAlphabetic(10);
    String wrongLogin = RandomStringUtils.randomAlphabetic(10);

    @Before
    @DisplayName("Метод создает курьера перед запускам каждого теста в текущем тестовом классе")
    public void createCourierBeforeEachTest() {
        StepToCreateCourier stepToCreateCourier = new StepToCreateCourier();
        stepToCreateCourier.creationCourier(login, password);
    }

    @After
    @DisplayName("After метод, который удаляет курьера после завершение каждого теста текущего тестового класса")
    public void cleanUpCouriersData () {
        StepToDeleteCourier stepToDeleteCourier = new StepToDeleteCourier();
        stepToDeleteCourier.deletionCourier(login, password);
    };

    private String login = RandomStringUtils.randomAlphabetic(10);
    private String password = RandomStringUtils.randomAlphabetic(10);
    private String firstName = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("Проверка на возможность залогиниться курьером")
    public void testCourierCanLogin () {
        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier(login, password);
        response.assertThat().statusCode(200).extract().path("id");
    }

    @Test
    @DisplayName("Проверяем, что невозможно авторизоваться отправляя \"\" (пустоту) вместо логина")
    public void testLoginWithEmptyLoginField () {
        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier("", password);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).statusCode(400);
    }

    @Test
    @DisplayName("Проверяем, что невозможно авторизоваться отправляя null вместо логина")
    public void testLoginWithNullToLogin () {
        PostCourierRequest postCourierRequest = new PostCourierRequest();
        ValidatableResponse response = postCourierRequest.loginCourier(null, password);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).statusCode(400);
    }

    @Test
    @DisplayName("Проверяем, что невозможно авторизоваться отправляя \"\" (пустоту) вместо пароля")
    public void testLoginWithEmptyPwField () {
        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier(login, "");
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).statusCode(400);
    }

    @Test
    @DisplayName("Проверяем, что невозможно авторизоваться отправляя null вместо пароля")
    public void testLoginWithNullPwField () {
        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier(login, null);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).statusCode(400);
    }

    @Test
    @DisplayName("Попыткая залогиниться курьером с помощью неправильного пароля")
    public void testLoginWithWrongPassword () {
        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier(login, wrongPassword);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).statusCode(404);
    }

    @Test
    @DisplayName("Попыткая залогиниться курьером с помощью неправильного логина")
    public void testLoginWithWrongLogin () {

        PostCourierRequest courierCanLogin = new PostCourierRequest();
        ValidatableResponse response = courierCanLogin.loginCourier(wrongLogin, password);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).statusCode(404);
    }

    @Test
    @DisplayName("Попытка авторизоваться под несуществущим пользователем")
    public void testLoginUnderNonExistentCourier () {

        StepToDeleteCourier stepToDeleteCourier = new StepToDeleteCourier();
        stepToDeleteCourier.deletionCourier(login, password);

        PostCourierRequest courierLogin = new PostCourierRequest();
        ValidatableResponse response = courierLogin.loginCourier(login, password);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).statusCode(404);
    }

    @Test
    @DisplayName("Проверка, возвращает ли система ID номер курьера")
    public void testIdIsReturnedAfterCourierIsCreated () {
        PostCourierRequest postCourierRequest = new PostCourierRequest();
        ValidatableResponse response = postCourierRequest.loginCourier(login, password);
        response.assertThat().statusCode(200).and().extract().path("id");


    }

}

