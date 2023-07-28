package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage{
    //Mapeamento


    //Construtor
    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Funções e métodos
    public String lerTituloDaPagina(){
        return lblTituloPagina.getText(); //Retorna o que estiver escrito no elemento
    }

    public void clicarNoTituloDoProduto(String productId){
        String idDinamico = "item_ " + productId + "_title_link";
        driver.findElement(By.id(idDinamico)).click();
    }



}
