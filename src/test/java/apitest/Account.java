//Pacote
package apitest;
// Biblioteca

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// Classe
public class Account {
    //3.1 Atributos
    String userId;
    //3.1.2 - Instanciar Classe Externa
    Gson gson = new Gson(); //Instancia o objeto de conversão de classe para json

    //3.2 Métodos e Funções
    // Método #1 - Criar usuário
    @Test
    public void testCreateUser(){
        //Arrange - Configura
        AccountEntity account = new AccountEntity(); //Chama a entidade usuário
        account.userName = "LeoGarson1"; //userName - Entrada e saída (resultado esperado)
        account.password = "12345678@Leo"; //password -   Entrada

        String jsonBody = gson.toJson(account); //Converte a entidade usuário no formato Json

        //Act - Executa

        //Dado - Quando - Então
        //Given - When - Then
        Response resposta = (Response) given()
                .contentType("application/json")    //Tipo de conteúdo
                .log().all()                        //Registre tudo
                .body(jsonBody)                     //Corpo da mensagem
        .when()
                .post("https://bookstore.toolsqa.com/Account/v1/User")

        //Assert - Valida
        .then()
                .log().all()                        //Registre tudo na volta
                .statusCode(201)    //Valide o código
                .body("username", is(account.userName)) //Valida o usuário
                .extract()

        ; //Fim da linha do REST-assured

        //Extrair o User ID (Identificação do usuário)
        userId = resposta.jsonPath().getString("userId");
        System.out.println("UserId extraido: " + userId);

    } //Fim do método de criação do usuário

    public void testGenerateToken(){
        //Configura

        //Executa

        //Valida

    } //Fim de método de criação de usuário

}
