// 1 - Pacote
package br.com.iterasys;
//2 - Bibliotecas

import juntos.Calculadora2;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

//3 - Classe
public class Calculadora2Test {
    //3.1 - Atributos

    //3.2 - Funções e Métodos
    @Test
    public void testeSomar(){
        //Configurar (Arrange)
        double num1 = 5;
        double num2 = 7;

        double resultadoEsperado = 12;

        //Executar (Act)
        double resultadoAtual = Calculadora2.somar(num1, num2);

        //Validar (Assert)
        Assert.assertEquals(resultadoAtual, resultadoEsperado);

    }
}
