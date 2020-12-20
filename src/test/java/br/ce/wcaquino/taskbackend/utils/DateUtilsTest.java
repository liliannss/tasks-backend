package br.ce.wcaquino.taskbackend.utils;


import br.ce.wcaquino.taskbackend.DateUtils;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {

        //Cenário
        LocalDate date = LocalDate.of(2030, 01, 01);

        //Execução
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);

        //Asserção
        assertEquals(true, equalOrFutureDate);
    }


    @Test
    public void deveRetornarFalseParaDatasPassadas() {

        //Cenário
        LocalDate date = LocalDate.of(2010, 01, 01);

        //Execução
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);

        //Asserção
        assertEquals(false, equalOrFutureDate);
    }

    @Test
    public void deveRetornarTrueParaDataAtual() {

        //Cenário dinâmico
        LocalDate date = LocalDate.now();

        //Execução
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);

        //Asserção
        assertEquals(true, equalOrFutureDate);
    }

}