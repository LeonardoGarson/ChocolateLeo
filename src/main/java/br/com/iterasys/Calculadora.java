package br.com.iterasys;

public class Calculadora {

    public static int somarInteiros(int num1, int num2){
        int soma = num1 + num2;
        System.out.println("O resultado de " + num1 +" + " + num2 + " = " +soma);
        return num1 + num2;
    }
    public static int subtrairInteiros(int num1, int num2){
        int subtrair = num1 - num2;
        System.out.println("O resultado de " + num1 + " - " + num2 + " = " + subtrair);
        return num1 + num2;
    }

    public static int multiplicarInteiros(int num1, int num2) {
        int multiplicar = num1 * num2;
        System.out.println("O resultado de " + num1 + " * " + num2 + " = " + multiplicar);
        return num1 + num2;
    }

    public static int dividirInteiros(int num1, int num2) {
        int divisao = num1 / num2;
        System.out.println("O resultado de " + num1 + " / " + num2 + " = " + divisao);
        return num1 + num2;
    }

}
