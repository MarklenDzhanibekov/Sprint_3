package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierCredentialsModel;
import requests.courier.PostCourierRequest;


public class StepToGetCourierId {
    int courierId = 0;
       @Step("Шаг для получения ID курьера при помощи данных курьера")
       public int getCourierIdByCredentials(CourierCredentialsModel generatedCourierCredentials) {

           PostCourierRequest postCourierRequest = new PostCourierRequest();
           ValidatableResponse response = postCourierRequest.loginCourier(generatedCourierCredentials);


           if (response.extract().statusCode() == 200) {
               courierId = response.assertThat().extract().path("id");
           } else {
               System.out.println("Courier ID не может быть извлечен");
           }
           return  courierId;
       }

       @Step("Шаг для получения ID курьера при объекта класса ValidatableResponse")
       public int getCourierIdByResponse( ValidatableResponse loginResponse) {

           if (loginResponse.extract().statusCode() == 200) {
               courierId = loginResponse.assertThat().extract().path("id");
           } else {
               System.out.println("Courier ID не может быть извлечен");
           }
           return  courierId;
       }
}
