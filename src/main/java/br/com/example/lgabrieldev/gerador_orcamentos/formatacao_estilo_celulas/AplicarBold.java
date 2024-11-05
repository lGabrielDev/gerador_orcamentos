package br.com.example.lgabrieldev.gerador_orcamentos.formatacao_estilo_celulas;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.models.item.Item;

@Component
public class AplicarBold {

    public XSSFRichTextString destacarPalavraEmNegrito(Workbook workbook, Item itemAtual){
        //criamos uma font
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        String descricaoItemCompleta = itemAtual.getDescricaoAndAcabamentoAndMedidas();
        String palavraParaBold =  String.format("Item %d", itemAtual.getId());
        
        Integer indexDaPalavraEncontrada = descricaoItemCompleta.indexOf(palavraParaBold);
        Integer endIndex = indexDaPalavraEncontrada + (palavraParaBold.length() -1);

        XSSFRichTextString wordFormatada = new XSSFRichTextString(descricaoItemCompleta);
        wordFormatada.applyFont(indexDaPalavraEncontrada, endIndex, boldFont);
        return wordFormatada;
    }

    public XSSFRichTextString palavraEmNegrito(Workbook workbook, String text){
        //criamos uma font
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        XSSFRichTextString wordFormatada = new XSSFRichTextString(text);
        wordFormatada.applyFont(0, text.length(), boldFont);
        return wordFormatada;
    }
}
