package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    //O mapeamento cria ou reflete uma DSL (Domain Structured Language)
    //(Linguagem estrutura de domínio) --> Como é falado na empresa

    //Elementos Web (WebElements)   --> Que serão Mapeados
    @FindBy(id = "user-name")   //Mapeia o elemento pelo seletor
    WebElement txtUsuario;

    @FindBy(id = "password")
    WebElement txtSenha;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    //Construtor (constructor)

    public HomePage(WebDriver driver) {
        super(driver);                                  //Instancia a classe mas como superclasse
        PageFactory.initElements(driver, this); //Conecta o objeto interno e o externo do Selenium
    }

    //Funções e métodos
    public void logar(String user, String password){
        txtUsuario.sendKeys(user);
        txtSenha.sendKeys(password);
        btnLogin.click();
    }



}
