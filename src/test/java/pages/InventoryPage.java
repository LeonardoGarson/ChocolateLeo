package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage {
    //Mapeamento
    @FindBy(css = "span.title")
    WebElement lbnTituloPagina;

    @FindBy(id = "item_4_title_link")
    WebElement lnkTituloProduto;

    //Construtor

    //Funções e métodos
}
