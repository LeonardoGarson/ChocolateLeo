# #language: pt
#  Funcionalidade: Selecionar produto na loja
#    Cenario: Selecionar produto com sucesso
#      Dado que acesso a loja SauceDemo
#      Quando preencho o usuario e senha
#      E clica em login
#      Entao exibe o titulo da pagina como "Products"
#      E exibe o link do carrinho de compras
#      Quando clina no produto "Sauce Labs Backpack"

Feature: Select product in the store
  Scenario: Select product successfully
    Given I access the SauceDemo store
    When I fill in the username "standard_user" and password "secret_sauce"
    And I click on login
    Then the page title is displayed as "Products"
    And the shopping cart link is displayed
    When I click on the "4" product
    Then I check the title of the product "Sauce Labs Backpack"
    And I check the price of the product "$29.99"
    When I click in Add to Cart
    And I click in Cart icon
    Then I check the page's title "Your Cart"
    And I check the title of the product "Sauce Labs Backpack"
    And I check the price of the product "$29.99"
    And I check if the quantity of the product is "1"
