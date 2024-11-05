package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas;

import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas.imp.PreencherPlanilhaImp;
import br.com.example.lgabrieldev.gerador_orcamentos.formatacao_estilo_celulas.AplicarBold;
import br.com.example.lgabrieldev.gerador_orcamentos.models.item.Item;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.Orcamento;

@Component
public class InserirDadosPlanilhaDashboard implements PreencherPlanilhaImp{

    //attributes
    private AplicarBold aplicarBold;

    //constructors
    public InserirDadosPlanilhaDashboard(AplicarBold aplicarBold){
        this.aplicarBold = aplicarBold;
    }

    public void limparDadosDashboard(List<Row> rowsOrcamentoItems){
        for(int i = 0; i < rowsOrcamentoItems.size(); i++){
            
            Row rowAtual = rowsOrcamentoItems.get(i);

            rowAtual.getCell(1).setCellValue("");
            rowAtual.getCell(2).setCellValue("");
            rowAtual.getCell(3).setCellValue(0);
            rowAtual.getCell(4).setCellValue("");
            rowAtual.getCell(5).setCellValue(0);

            //definimos a altura dessas row
            rowsOrcamentoItems.get(i).setHeight((short) (90 * 20));
        }
    }

    @Override
    public void preencherPlanilha(Workbook workbook, Orcamento dadosOrcamento, String filePath){
            Sheet orcamentSheet = workbook.getSheetAt(0);
            //rows do dashboard
            Row rowItem1 = orcamentSheet.getRow(16);
            Row rowItem2 = orcamentSheet.getRow(18);
            Row rowItem3 = orcamentSheet.getRow(20);
            Row rowItem4 = orcamentSheet.getRow(22);
            Row rowItem5 = orcamentSheet.getRow(24);
            Row rowItem6 = orcamentSheet.getRow(26);

            List<Row> rowsOrcamentoItems = List.of(rowItem1, rowItem2, rowItem3, rowItem4, rowItem5, rowItem6);

            //limpamos os dados
            this.limparDadosDashboard(rowsOrcamentoItems);

            //alteramos os dados da row atual
            List<Item> items = dadosOrcamento.getItems();

            for(int i = 0; i < items.size(); i++){

                Item itemAtual = items.get(i);
                Row rowAtual = rowsOrcamentoItems.get(i);
                
                //item1 -> Em negrito
                String itemId = "0" + itemAtual.getId() ;
                XSSFRichTextString itemIdEmNegrito = this.aplicarBold.palavraEmNegrito(workbook, itemId);

                rowAtual.getCell(1).setCellValue(itemIdEmNegrito);
                rowAtual.getCell(2).setCellValue(itemAtual.getDescricaoAndAcabamentoAndMedidas());
                rowAtual.getCell(3).setCellValue(itemAtual.getValorUnit());
                rowAtual.getCell(4).setCellValue(itemAtual.getQuantity());
                rowAtual.getCell(5).setCellValue(itemAtual.getValorTotal());  
            }

            //total
            Cell totalCell = orcamentSheet.getRow(29).getCell(5);
            totalCell.setCellValue(dadosOrcamento.getValorTotal());

            // //stilo de alinhamento em cima
            // // CellStyle alinharEmCima = workbook.getCellStyleAt(0);
            // // alinharEmCima.setVerticalAlignment(VerticalAlignment.TOP);

            // //alteramos o arquivo
            // try(FileOutputStream fos = new FileOutputStream(filePathNovo)){
            //     workbook.write(fos);
            // }

            //salvamos o arquivo
            try(FileOutputStream fos = new FileOutputStream(filePath)){
                workbook.write(fos);
            }
            catch(Exception e){
                throw new RuntimeException(filePath + " --> Arquivo nao encontrado 2");
            }
    }
}