package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage{
    //Mapeamento


    //Construtor
    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Funções e métodos
   public void clicarNoTituloDoProduto(String productId){
        String idDinamico = "item_ " + productId + "_title_link";
        driver.findElement(By.id(idDinamico)).click();
   }



}
