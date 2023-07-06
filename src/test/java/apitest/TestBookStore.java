package apitest;

import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.*;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestBookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; //Endereço Base
    String ct = "application/json"; //ContentType da API

    TestAccount account = new TestAccount();    // Instâcia a classe Account
    LoanEntity isbn = new LoanEntity();         //Instâcia a classe de livros
    Gson gson = new Gson();


    //@BeforeMethod   //Antes de cada teste
    @BeforeClass    //Antes da Classe
    public void setUp(ITestContext context){

        account.testCreateUser(context);        //Cria novo usuário
        account.testGenerateToken(context);     //Gera um novo Token
    }

    //@AfterMethod    //Depois de cada teste
    @AfterClass     //Depois da Classe
    public void tearDown() throws InterruptedException {

        account.testDeleteUser();               //Exclui o usuário
    }

    @Test(priority = 1)
    public void testResearchBook(ITestContext context){
        //Configura
        //Dados de entrada não são requeridos - Basta chamar o endpoint
        //Resultado esperado é somente o StatusCode 200 e um json com a lista de livros

        //Executa
        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Books") //Consultar a lista de todos os livros
        //Valida
        .then()
                .log().all()
                .statusCode(200)
        ;
                

    }

    @Test(priority = 2)
    public void testLoanBooks(ITestContext context){ // Emprestar livros
        // Configura
        // Dados de entrada
        // userId virá pelo context
        isbn.userId = context.getAttribute("userID").toString(); //Código usuário
        isbn.collectionOfIsbns = new LoanEntity.ISBN[] {
                new LoanEntity.ISBN("9781449325862")                    //Código livro
        }
        ;


        // Dados de saida
        // statusCode = 201
        // Retorne o isbn do livro emprestado (echo)

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .post(uri + "Books")
        // Valida
        .then()
                .log().all()
                .statusCode(201)
                .body("isbn", is(isbn.isbn))
                ;

    }

    @Test(priority = 3)
    public void testUpdateLoan(ITestContext context){       //Atualiza quem está com o Livro
        //Configura
        //Dados de entrada
        //UserId é extraído no BeforeMethod
        String isbnAntigo = "9781449325862"; //Livor antigo
        String isbnNovo = "9781449331818";   //Novo livro

        //Alimentar classe LoanEntity apenas com o código do usuário e o isbn
        isbn.userId = context.getAttribute("userID").toString();
        isbn.isbn = isbnNovo;     //Código do livro que estará com o usuário

        //Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .put(uri + "Books/" + isbnAntigo)
        // Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("books[0].isbn", is(isbnNovo))
        ;


    }
    @Test(priority = 4)
    public void testDeleteLoans(ITestContext context){       //Deletar
        //Configura
        //Dados de entrada
        //UserId é extraído no BeforeClass
        //statusCode 204

        //Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .delete(uri + "Books?UserId=" + context.getAttribute("userID"))
        // Valida
        .then()
                .log().all()
                .statusCode(204)
        ;

    }



}
