package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class CheckFile {
    
    // ============ verificar se o arquivo existe ============
    public Workbook arquivoExiste(String filepath){
        try{
            FileInputStream fis = new FileInputStream(filepath);
            return new XSSFWorkbook(fis);
        }
        catch(IOException e){
            throw new RuntimeException("Arquivo não encontrado --> ".concat(filepath) + "\nO erro aconteceu no method arquivoExiste() na class CheckFile");
        }
    }
}