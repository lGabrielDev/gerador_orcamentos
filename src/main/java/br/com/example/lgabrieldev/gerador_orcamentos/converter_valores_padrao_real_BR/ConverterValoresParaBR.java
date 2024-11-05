package br.com.example.lgabrieldev.gerador_orcamentos.converter_valores_padrao_real_BR;

import java.text.NumberFormat;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class ConverterValoresParaBR {
    public String converterDoubleToBrazil(Double valor){

        NumberFormat formatoBrPadrao = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatoBrPadrao.setMinimumFractionDigits(2);
        formatoBrPadrao.setMaximumFractionDigits(2);
        return  formatoBrPadrao.format(valor);
    }
}