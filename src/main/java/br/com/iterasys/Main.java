package br.com.iterasys;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        chamarEncomenda();
        Calculadora.somarInteiros(5,7);
        Calculadora.subtrairInteiros(7,5);
        Calculadora.multiplicarInteiros(7,5);
        Calculadora.dividirInteiros(10,5);
    }
    public static void chamarEncomenda(){
        int barras = 20;

        Encomenda.calcularBarrasDeChocolatePorCaixa(barras);

    }
}