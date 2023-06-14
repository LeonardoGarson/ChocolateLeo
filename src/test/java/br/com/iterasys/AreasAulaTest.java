package br.com.iterasys;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AreasAulaTest {
    @Test
    public void testarCalcularQuadrado(){
        //Configura
        double lado = 3;
        double resultadoEsperado = 9;

        //Executa
        double resultadoAtual = AreasAula.calcularQuadrado(lado);

        //Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularRetangulo() {
        // Configura
        double largura = 4;
        double altura = 5;
        double resultadoEsperado = 20;

        // Executa
        double resultadoAtual = AreasAula.calcularRetangulo(largura, altura);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularTriangulo() {
        // Configura
        double base = 6;
        double altura = 8;
        double resultadoEsperado = 24;

        // Executa
        double resultadoAtual = AreasAula.calcularTriangulo(base, altura);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularCirculo() {
        // Configura
        double raio = 2;
        double resultadoEsperado = 12.566370614359172;

        // Executa
        double resultadoAtual = AreasAula.calcularCirculo(raio);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

}
