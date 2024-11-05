package br.com.example.lgabrieldev.gerador_orcamentos.validacoes.orcamento_validations;

import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FieldCannotBeNullException;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FieldNumberMustBeBigger;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.OrcamentoCreate;
import br.com.example.lgabrieldev.gerador_orcamentos.validacoes.interfaces.AllValidationsAreGood;
import br.com.example.lgabrieldev.gerador_orcamentos.validacoes.interfaces.SinalAndParcelasImp;

@Component
public class SinalValidations implements AllValidationsAreGood, SinalAndParcelasImp{
    
    // ====== must be bigger than R$ 1.000,00 ======
    public Boolean sinalIsGreaterThan1000(Double sinal){

        if(sinal < 1000){
            throw new FieldNumberMustBeBigger(" 'sinal' must be greater than R$ 1.000,00");
        }
        return true;
    }

    // ====== if sinal was filled, 'parcelas' must be filled as well ======
    @Override
    public Boolean sinalAndParcelasMustBeFilledTogether(Double sinal, Integer parcelas){

        if(parcelas == null){
            throw new FieldCannotBeNullException(" 'sinal' and 'parcelas' must be filled together");
        }
        return true;
    }

    // ====== all validations passed ======
    @Override
    public Boolean allValidationsAreGood(OrcamentoCreate orcamentoCreate) {
        if(orcamentoCreate.getSinal() != null){
            this.sinalIsGreaterThan1000(orcamentoCreate.getSinal());
            this.sinalAndParcelasMustBeFilledTogether(orcamentoCreate.getSinal(), orcamentoCreate.getParcelas());
        }
        return true;
    }
}