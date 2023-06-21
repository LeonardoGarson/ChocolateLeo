//Pacote
package apitest;
// Biblioteca

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//Classe
public class Account {
    //3.1 Atributos
    String userId;
    String ct = "application/json"; //ContentType da API
    String jsonBody; //Guardar o json que será enviado
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; //Endereço Base
    Response resposta;
    String token; //Guardar o token de identificação do usuário

    //3.1.2 - Instanciar Classe Externa
    Gson gson = new Gson(); //Instancia o objeto de conversão de classe para json
    AccountEntity account = new AccountEntity(); //Chama a entidade usuário
    //3.2 Métodos e Funções

    // Método #1 - Criar usuário
    @Test(priority = 1)
    public void testCreateUser(){
        //Arrange - Configura
        account.userName = "LeoGarson23";   //userName - Entrada e saída (resultado esperado)
        account.password = "12345678@Leo";  //password -   Entrada

        jsonBody = gson.toJson(account); //Converte a entidade usuário no formato Json

        //Act - Executa

        //Dado - Quando - Então
        //Given - When - Then
        resposta = (Response)
        given()
                .contentType(ct)        //Tipo de conteúdo
                .log().all()            //Registre tudo
                .body(jsonBody)         //Corpo da mensagem
        .when()
                    .post(uri + "User")
        //Assert - Valida
        .then()
                .log().all()                                    //Registre tudo na volta
                .statusCode(201)                //Valide o código
                .body("username", is(account.userName))    //Valida o usuário
                .extract()

        ; //Fim da linha do REST-assured

        //Extrair o User ID (Identificação do usuário)
        userId = resposta.jsonPath().getString("userID");
        System.out.println("UserId extraido: " + userId);

    } //Fim do método de criação do usuário

    @Test(priority = 2)
    public void testGenerateToken(){
        //Configura
            //--> Dados de entrada são fornecidos pela AccountEntity
            //--> Resultado esperado é que ele receba um token
        //Executa
       resposta = (Response)
       given()
                    .contentType(ct)
                    .log().all()
                    .body(jsonBody)
       .when()
                    .post(uri + "GenerateToken")
       .then()
                   .log().all()
                   .statusCode(200) // valida a comunicação
                   .body("status", is("Success")) // Status = Sucesso
                   .body("result", is("User authorized successfully."))
                   .extract()
            ;

       //Extração do toke
       token = resposta.jsonPath().getString("token");

        //Valida
        Assert.assertTrue(token.length() != 0);

    } //Fim de método de criação de usuário

    @Test(priority = 3)
    public void testAuthorized(){
        //Configura
        //Dados de entrada
        // Serão fornecidos pela AccountEntity através do método testCreateUser - priority = 1

        //Dados de saída / Resultado esperado
        //StatusCode = 200
        //Response Body = True

        //Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "Authorized")
        //Valida
        .then()
                .log().all()
                .statusCode(200)
                // .body(true) // ToDo: como validar o retorno do body apenas como true
        ;

    }

    @Test(priority = 4)
    public void testResearchUserNotAuthorized(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no método testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // Status Code = 401, Code = 1200 e Message = User not authorized!

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
        .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usuário pelo userId
                // Valida
        .then()                                     // Então
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(401)     // Valida se não está autorizado
                .body("code", is("1200")) // Valida o código de mensagem "não autorizado"
                .body("message", is("User not authorized!"))
        ;                                           // Conclui o bloco do REST-assured
    }

    @Test(priority = 5)
    public void testResearchUser(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no método testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // userName virá da classe Account e o status code deve ser 200

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
                .header("Authorization", "Bearer " + token)
        .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usuário pelo userId
                // Valida
        .then()                                     // Então
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(200)     // Valida se a conexão teve sucesso
                .body("userId", is(userId))
                .body("username", is(account.userName)) // Valida o nome do usuário
        ;                                           // Conclui o bloco do REST-assured
    }

    @Test(priority = 6)
    public void testDeleteUser(){
        //Configura
        //Dados de entrada vem do método de testes da criação do usuário
        //Resultado esperado é o código e a mensagem de sucesso da exclusão do usuário

        //Executa
        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(uri + "User/" + userId)

        ///Valida
        .then()
                .log().all()
                .statusCode(204)
        ;
//teste
    }

}