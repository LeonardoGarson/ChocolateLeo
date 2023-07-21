package steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class selectProduct {
    //Atributos
    static WebDriver driver;

    @BeforeAll //Executa antes de todos os blocos de passos --> Usar do cucumber
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();        //Instância o objeto de opções do ChromeDriver
        options.addArguments("--remote-allow-origins=*");   //Permite qualquer origem remota
        WebDriverManager.chromedriver().setup();            //Baixar a versão mais atual do ChromeDriver
        driver = new ChromeDriver(options);                 //Instancia o objeto selenium como ChromeDriver

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000)); //Estabelece uma espera implícita para carregar qualquer elemento
        driver.manage().window().maximize();                                //Maximiza a janela
    }
    @AfterAll   //Executa após todos os blocos de passos --> Usar o mesmo do Before
    public static void tearDown(){
        driver.quit();   //Encerra o objeto do selenium WebDriver

    }


    @Given("I access the SauceDemo store")
    public void i_access_the_sauce_demo_store() {
        driver.get("https://www.saucedemo.com");
    }

    @When("I fill in the username {string} and password {string}")
    public void i_fill_in_the_username_and_password(String user, String password) {
        driver.findElement(By.id("user-name")).sendKeys(user);          //Escreve o conteúdo da variável user
        driver.findElement(By.id("password")).sendKeys(password);    //Escreve o conteúdo da variável password
    }

    @And("I click on login")
    public void i_click_on_login() {
        driver.findElement(By.id("login-button")).click();              //Clica no botão login
    }

    // "@Then("the page title is displayed as {string}")" --> Inutilizado po deixar a frase igual
    @Then("I check the page's title {string}")
    public void the_page_title_is_displayed_as(String pageTitle) {
        //Verifica se o título da página coíncide com o informado
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), pageTitle);
    }

    @And("the shopping cart link is displayed")
    public void the_shopping_cart_link_is_displayed() {
        //Verifica se o elemento do carrinho de compras esta visível
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
    }

    @When("I click on the {string} product")
    public void i_click_on_the_product(String productId) {
        driver.findElement(By.id("item_" + productId + "_title_link")).click();    //Clica no elemento correspondente ao código do produto informado
    }

    @Then("I check the title of the product {string}")
    public void i_check_the_title_of_the_product(String productTitle) {
        //Verifica se o título do produto corresponde ao informado no feature
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_name.large_size")).getText(), productTitle);
    }

    @And("I check the price of the product {string}")
    public void i_check_the_price_of_the_product(String productPrice) {
        //Verifica se o preço do produto corresponde ao informado
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText(), productPrice);
    }

    @When("I click in Add to Cart")
    public void i_click_in_add_to_cart() {
        //Clica no botão adicionar ao carrinho
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("I click in Cart icon")
    public void i_click_in_cart_icon() {
        //Clica no icone do carrinho
        driver.findElement(By.id("shopping_cart_container")).click();
    }


    @And("I check if the quantity of the product is {string}")
    public void i_check_if_the_quantity_of_the_product_is(String quantity) {
        assertEquals(driver.findElement(By.cssSelector("div.cart_quantity")).getText(), quantity);
    }

    @Then("I check the title of the product {string} in cart")
    public void i_check_the_title_of_the_product_in_cart(String productTitle) {
        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_name")).getText(), productTitle);
    }

    @Then("I check the price of the product {string} in cart")
    public void i_check_the_price_of_the_product_in_cart(String productPrice) {
        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_price")).getText(), productPrice);

    }

//

}
