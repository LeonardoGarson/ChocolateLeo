//1 - Pacote
package br.com.iterasys;
//2 - Bibliotecas


//3 - Classe
public class Encomenda {
    //3.1 - Atributos

    //3.2 - Funções e Métodos
    public static int calcularBarrasDeChocolatePorCaixa(int barras){
        byte quantidadePorCaixa = 12;
        int caixas = barras / quantidadePorCaixa;
        int unidade = barras % quantidadePorCaixa;
        System.out.println("quantidade de Caixa: " + caixas);
        System.out.println("Com essas unidades: " + unidade);
        return caixas;
    }
}
