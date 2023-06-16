package apiPetSoter;

import com.google.gson.Gson;
import entities.UserEntity;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class User {
    //Atributos
    String jsonBody;
    String ct = "application/json";
    String uri = "https://petstore.swagger.io/v2/";

    Gson gson = new Gson(); //Instanciar

    @Test
    public void testCreateUser(){
        //Configura
        //Dados de Entrada
        UserEntity user = new UserEntity();
        user.id = 9999;
        user.userName = "LeonardoGarson";
        user.firstName = "Leonardo";
        user.lastName = "Garson";
        user.eMail = "leonardo_ewhg@hotmail.com";
        user.password = "234567";
        user.phone = "11991376212";
        user.userStatusString = 0;

        jsonBody = gson.toJson(user);

        //Dados de saída - Resultado esperado
        int code = 200;
        String type = "Unknown";
        String message = "9999";

        //Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "user")
        //Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(code))
                .body("type", is(type))
                .body("message", is(message))
                ;

    }
}
