package br.com.example.lgabrieldev.gerador_orcamentos;

import org.springframework.stereotype.Service;
import br.com.example.lgabrieldev.gerador_orcamentos.criar_orcamento.GeradorOrcamentos;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.Orcamento;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.OrcamentoCreate;
import br.com.example.lgabrieldev.gerador_orcamentos.servicosWeb.ServicosWeb;
import br.com.example.lgabrieldev.gerador_orcamentos.validacoes.GeneralValidations;

@Service
public class OrcamentoService {
    
    //attributes
    private GeneralValidations generalValidations;
    private GeradorOrcamentos geradorOrcamentos;
    private ServicosWeb servicosWeb;

    //constructors
    public OrcamentoService(GeneralValidations generalValidations, GeradorOrcamentos geradorOrcamentos, ServicosWeb servicosWeb){
        this.generalValidations = generalValidations;
        this.geradorOrcamentos = geradorOrcamentos;
        this.servicosWeb = servicosWeb;

    }

    // ======================= CREATE =======================
    public String createOrcamento(OrcamentoCreate orcamentoCreate){

        //validations
        this.generalValidations.validateAllAttributes(orcamentoCreate);
        Orcamento dadosOrcamento = new Orcamento(orcamentoCreate);

        //gerar arquivos
        String pdfPath = this.geradorOrcamentos.criarOrcamento(dadosOrcamento);

        this.servicosWeb.enviarPdfWhats(pdfPath);
        //this.servicosWeb.uploadPdfDrive(pdfPath);

        return "PDF enviado com sucesso!";
    }
}