package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas;

import java.io.FileOutputStream;
import java.time.LocalDate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.converter_valores_padrao_real_BR.ConverterValoresParaBR;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas.imp.PreencherPlanilhaImp;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.Orcamento;

@Component
public class InserirDadosPlanilhaDados implements PreencherPlanilhaImp{

    //attributes
    private ConverterValoresParaBR converterValoresParaBR;

    //constructors
    public InserirDadosPlanilhaDados(ConverterValoresParaBR converterValoresParaBR){
        this.converterValoresParaBR = converterValoresParaBR;
    }

    @Override
    public void preencherPlanilha(Workbook arquivoExcel, Orcamento dadosOrcamento, String filepath){
        Sheet sheetDados = arquivoExcel.getSheetAt(1);
        
        //orcamento number
        Cell lastOrcamentoNumberCell = sheetDados.getRow(1).getCell(1);
        Double atualOrcamentoNumber = (lastOrcamentoNumberCell.getNumericCellValue() + 1);
        lastOrcamentoNumberCell.setCellValue(atualOrcamentoNumber);
        
        //orcamentoNumber + date
        Cell cellOrcamentoNumbe = sheetDados.getRow(2).getCell(1);
        String orcamentoNumberPlusDate = String.format("Orçamento #%.0f/%d\n%s", atualOrcamentoNumber, LocalDate.now().getYear(), dadosOrcamento.getDataEnvio());
        cellOrcamentoNumbe.setCellValue(orcamentoNumberPlusDate);
        
        //cliente
        Cell clienteCell = sheetDados.getRow(4).getCell(1);
        clienteCell.setCellValue(dadosOrcamento.getCliente());

        //descricao
        Cell descricaoRowCell = sheetDados.getRow(5).getCell(1);
        descricaoRowCell.setCellValue(dadosOrcamento.getDescricao());

        //observacoes
        Cell observacoesCell = sheetDados.getRow(6).getCell(1);
        observacoesCell.setCellValue(dadosOrcamento.getObservacoes());

        //forma de pagamento
        String formaDePagamento = "";
        if(dadosOrcamento.getSinal() != 0){
            formaDePagamento = String.format("Sinal de R$ %s + %dx de R$ %s no cartão",
                this.converterValoresParaBR.converterDoubleToBrazil(dadosOrcamento.getSinal()),
                dadosOrcamento.getParcelas(),
                this.converterValoresParaBR.converterDoubleToBrazil(dadosOrcamento.getCalcularParcela()));
        }
        Cell formaDePagamentoCell = sheetDados.getRow(7).getCell(1);
        formaDePagamentoCell.setCellValue(formaDePagamento);
        
        //prazo de entrega
        Cell prazoDeEntregaCell = sheetDados.getRow(8).getCell(1);
        if(dadosOrcamento.getPrazoEntrega() == null){
            prazoDeEntregaCell.setCellValue("");
        }
        else{
            prazoDeEntregaCell.setCellValue(dadosOrcamento.getPrazoEntrega() + " dias");
        }

        //salvamos o arquivo
        try(FileOutputStream localSalvar = new FileOutputStream(filepath)){
            arquivoExcel.write(localSalvar);
        }
        catch(Exception e){
            throw new RuntimeException(
                "It hasn't saved because the output filepath doesn't exists\n" + 
                "errorMethod ->  preencherPlanilhaDados()\n" +
                "Class error -> InserirDadosPlanilhaDados"
            );
        }
    }
}