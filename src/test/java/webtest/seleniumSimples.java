//Pacote
package webtest;
//Biblioteca

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//Classe
public class seleniumSimples {
    //3.1 Atributos
    WebDriver driver;           //Objeto do Selenium Driver

    //3.2 Funções e Métodos de apoio
    //Não iremos criar

    //3.3 Antes do Teste
    @BeforeMethod
    public void setUp(){
        //Instalar e configura o driver do navegador/browser
        WebDriverManager.chromedriver().setup();    //Efetua o download e instalação do Chrome Driver

        //Configura as opções para o driver do navegador (a partir do Selenium 4.0)
        ChromeOptions options = new ChromeOptions(); //Objeto de configuração para o Chrome
        options.addArguments("--remote-allow-origins=*");   ///Permitir qualquer origem remota

        //Instânciar o Selenium como o driver do navegador
        driver = new ChromeDriver(options); //Instância o Selenium para o ChromeDriver com opções
        //Configura o tempo geral de espera de elementos em até 5 segundos
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();    //Maxima a janela do navegador
    }

    //3.4 Depois do Teste
    @AfterMethod
    public void tearDown(){
        driver.quit();  //Destrói o objeto do Selenium WebDriver
    }

    //3.5 Testes em Si
    @Test
    public void testarSelectBackDrop(){
        //Abrir a página inicial do side SauceDemo
        driver.get("https://www.saucedemo.com");

        //Digitar usuário e senha
        //Clicar no campo antes de escrever
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();   //Vai clicar no campo user name
        username.clear();   //Limpa a caixa de texto
        username.sendKeys("standard_user"); //Escreve na caixa (Colar texto)
        //username.sendKeys(Keys.chord("standard_user")); //Escreve na caixa (Letra por letra)

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        //Transição da página / Carregamento da nova pagina (Lentidão)

        //Verificar se estamos na página interna (Se conseguimos entrar)
        //Verifica a palavra "Products" em determinado elemento
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), "Products");
        //Verifica se está presente o elemento do carrinho de compras
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());

        //Selecionar o produto que seria o ID nº4
        driver.findElement(By.id("item_4_title_link")).click();
        //Transição de tela para a página do produto

        //Validar o nome e o valor
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_name.large_size")).getText(), "Sauce Labs Backpack");
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText(), "$29.99");

        //Clicar no botão de adicionar no carrinho
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        //Clica no ícone do carrinho
        driver.findElement(By.id("shopping_cart_container")).click();
        //Transição de tela

        //Verificar título da página, quantidade, produto e valor
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), "Your Cart");
        assertEquals(driver.findElement(By.cssSelector("div.cart_quantity")).getText(), "1");
        assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_price")).getText(), "$29.99");

    }


}
