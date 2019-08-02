package com.bpc;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class AlfaTest {

    private static final int AMOUNT = 22018;
    private static final String USER_NAME = "task-ibatulina-api";
    private static final String PASSWORD = "260719";

//    @Test
//    public void startReverseOrder(){
//        String orderID = createOrder();
//        payOrder(orderID);
//        reverseOrder(orderID);
//    }
//    @Test
//    public void startRefundOrder(){
//        String orderID = createOrder();
//        payOrder(orderID);
//        refundOrder(orderID, AMOUNT);
//    }
//    @Test
//    public void startDoubleRefundOrder(){
//        String orderID = createOrder();
//        payOrder(orderID);
//        refundOrder(orderID, AMOUNT/2);
//        refundOrder(orderID, AMOUNT/2);
//    }
    @Test
    public void startCreateOrder(){
        createOrder();
    }

    public String createOrder() {
        Response res= RestAssured
                .given()
               .baseUri("https://web.rbsdev.com")
               .basePath("/alfapayment-release/rest/register.do")
               .param("userName", USER_NAME)
               .param("password", PASSWORD)
               .param("orderNumber", "7623574274527")
               .param("amount", AMOUNT)
               .param("returnUrl", "http://ya.ru/")
               .when()
               .post().then().statusCode(200)
               .extract().response();
        String response = res.asString();
        JsonPath jsonRes = new JsonPath(response);
        String orderID = jsonRes.getString("orderId");
        Assert.assertNotNull(orderID, "Error create order");
        System.out.println(orderID);
        return orderID;
    }

    public void payOrder(String orderID) {
        //ручной шаг, потому что body зашифровано, если я правильно поняла, то для оплаты через платежную страницу используется метод start.do и finish.do, у него как раз зашифровано body, например "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"
    }

    public void reverseOrder(String orderID){
        Response res= RestAssured.given()
                .baseUri("https://web.rbsdev.com")
                .basePath("/alfapayment-release/rest/reverse.do")
                .param("userName", USER_NAME)
                .param("password", PASSWORD)
                .param ("orderId", orderID)
                .when()
                .post().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        JsonPath jsonRes = new JsonPath(response);
        String errorCode = jsonRes.getString("errorCode");
        Assert.assertEquals("0", errorCode, "Error reverse order");
    }

    public void refundOrder(String orderID, int amount){
        Response res= RestAssured.given()
                .baseUri("https://web.rbsdev.com")
                .basePath("/alfapayment-release/rest/refund.do")
                .param("userName", USER_NAME)
                .param("password", PASSWORD)
                .param ("orderId", orderID)
                .param("amount", amount)
                .when()
                .post().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        JsonPath jsonRes = new JsonPath(response);
        String errorCode = jsonRes.getString("errorCode");
        Assert.assertEquals("0", errorCode, "Error refund order");
    }


}
