package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Função para retornar o título da guia do Browser
    public String lerTituloAba(){
        return driver.getTitle();
    }
}
