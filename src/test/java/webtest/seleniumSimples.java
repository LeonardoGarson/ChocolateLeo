//Pacote
package webtest;
//Biblioteca

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

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


}
