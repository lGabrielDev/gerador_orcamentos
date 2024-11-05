package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.gerar_pdf.GeradorPdf;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas.InserirDadosPlanilhaDados;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas.InserirDadosPlanilhaDashboard;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FileIsOpenException;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.Orcamento;

@Component
public class GeradorOrcamentos {

    //attributes
    String filePath = "src\\main\\resources\\static\\modelo_orcamento_and_script\\modelo_simples.xlsm";
    private CheckFile checkFile;
    private InserirDadosPlanilhaDados inserirDadosPlanilhaDados;
    private InserirDadosPlanilhaDashboard inserirDadosPlanilhaDashboard;    
    private GeradorPdf geradorPdf;

    //constructors
    public GeradorOrcamentos(CheckFile checkFile, InserirDadosPlanilhaDados inserirDadosNasPlanilhas, InserirDadosPlanilhaDashboard inserirDadosPlanilhaDashboard, GeradorPdf geradorPdf){
        this.checkFile = checkFile;
        this.inserirDadosPlanilhaDados = inserirDadosNasPlanilhas;
        this.inserirDadosPlanilhaDashboard = inserirDadosPlanilhaDashboard;
        this.geradorPdf = geradorPdf;
    }

    // ============ criar orcamento em EXCEL ============
    public String criarOrcamento(Orcamento dadosOrcamento){
        Workbook arquivoExcel = this.checkFile.arquivoExiste(this.filePath);

        try{
            this.inserirDadosPlanilhaDados.preencherPlanilha(arquivoExcel, dadosOrcamento, this.filePath);
            this.inserirDadosPlanilhaDashboard.preencherPlanilha(arquivoExcel, dadosOrcamento, this.filePath);
            System.out.println(" ============== Excel file has been created! ============== ");

        }
        catch(Exception e){
            throw new FileIsOpenException(" You cannot update your excel file while it is open. Close it first.");
        }
        return this.geradorPdf.rodarScriptGerarPdf();
    } 
}