package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    @FindBy(id = "shopping_cart_container")
    WebElement imgCarrinho;

    @FindBy(css = "span-title")
    WebElement lblTituloPagina;

    @FindBy(css = "btn btn_primary btn_small btn_inventory")
    WebElement btnAdicionarOuRemoverNoCarrinho;

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Função para retornar o título da guia do Browser
    public String lerTituloAba(){
        return driver.getTitle();
    }

    public void clicarNoCarrinho(){
        imgCarrinho.click();        //Clica no carrinho
    }

    public String lerTituloPagina(){
        return lblTituloPagina.getText();
    }

    public String lerTextoDoBotaoAdicionarRemoverDoCarrinho(){
        return btnAdicionarOuRemoverNoCarrinho.getText();
    }

    public void clicarNoBotaoAdicionarOuRemoverNoCarrinho(){
        btnAdicionarOuRemoverNoCarrinho.click();
    }


}
