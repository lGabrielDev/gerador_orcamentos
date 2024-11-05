package br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.preencher_planilhas.imp;

import org.apache.poi.ss.usermodel.Workbook;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.Orcamento;

public interface PreencherPlanilhaImp {
    
    public void preencherPlanilha(Workbook workbook, Orcamento dadosOrcamento, String filePath);
}
