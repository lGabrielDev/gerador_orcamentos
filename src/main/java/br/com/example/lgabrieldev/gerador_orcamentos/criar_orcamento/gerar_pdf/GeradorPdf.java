package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.gerar_pdf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FilePathIsWrongException;

@Component
public class GeradorPdf {
   
    //attributes
    private String windowsScriptPath = "src\\main\\resources\\static\\modelo_orcamento_and_script\\script_criar_pdf.vbs";

    @Value("${REPO_PATH}")
    private String repoPath;

    private String pdfName = "orcamento.pdf";
    private String pdfPathToSave;

    //inicializar variable pdfPathToSave
    public void pdfAttributeSetup(){
        this.pdfPathToSave = repoPath + "\\whats_excel_complete\\src\\main\\resources\\static\\modelo_orcamento_and_script\\" + this.pdfName;
    }
 
    //criamos o script
    public void criarArquivoVbsCript(){

        String script = String.format(
            "Dim objExcel, objWorkbook\r\n" + //
            "\r\n" + //
            "' Defina o caminho do arquivo do Excel e o caminho de destino do PDF\r\n" + //
            "filePathExcel = \"%s\\whats_excel_complete\\src\\main\\resources\\static\\modelo_orcamento_and_script\\modelo_simples.xlsm\"\r\n" + //
            "filePathSalvarPdf = \"%s\"\r\n" + //
            "\r\n" + //
            "' Inicialize o objeto Excel\r\n" + //
            "Set objExcel = CreateObject(\"Excel.Application\")\r\n" + //
            "objExcel.Visible = False ' Não exibe o Excel\r\n" + //
            "\r\n" + //
            "' Abra o arquivo do Excel\r\n" + //
            "Set objWorkbook = objExcel.Workbooks.Open(filePathExcel)\r\n" + //
            "\r\n" + //
            "' Exporta a planilha ativa como PDF\r\n" + //
            "objWorkbook.ActiveSheet.ExportAsFixedFormat 0, filePathSalvarPdf ,0, 1, 0,,,0\r\n" + //
            "\r\n" + //
            "' Salve e feche o arquivo Excel\r\n" + //
            "objWorkbook.Close True\r\n" + //
            "objExcel.Quit\r\n" + //
            "\r\n" + //
            "' Limpeza\r\n" + //
            "Set objWorkbook = Nothing\r\n" + //
            "Set objExcel = Nothing",
            this.repoPath, this.pdfPathToSave
        );

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.windowsScriptPath));
            bw.write(script);
            bw.close();
            System.out.println(" *********** SCRIPT CRIADO COM SUCESSO! ***********");
        }
        catch(Exception e){
            throw new FilePathIsWrongException("Script folder not found!");
        }
    }

    public String rodarScriptGerarPdf(){
        this.pdfAttributeSetup();
        this.criarArquivoVbsCript();

        try{
            Runtime.getRuntime().exec("wscript " + this.windowsScriptPath);
            System.out.println(" *********** PDF FILE has been created! ***********");
            return this.pdfPathToSave;
        }
        catch(IOException e){
            
            throw new RuntimeException(windowsScriptPath + " File not found. Path is wrong!");
        }
   }
}