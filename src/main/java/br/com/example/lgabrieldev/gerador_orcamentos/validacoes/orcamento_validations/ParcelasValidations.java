package br.com.example.lgabrieldev.gerador_orcamentos.validacoes.orcamento_validations;

import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FieldCannotBeNullException;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FieldNumberMustBeBigger;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.OrcamentoCreate;
import br.com.example.lgabrieldev.gerador_orcamentos.validacoes.interfaces.AllValidationsAreGood;
import br.com.example.lgabrieldev.gerador_orcamentos.validacoes.interfaces.SinalAndParcelasImp;

@Component
public class ParcelasValidations implements AllValidationsAreGood, SinalAndParcelasImp{
    
    // ====== must be bigger than 1 ======
    public Boolean parcelasIsGreaterThan1(Integer parcelas){

        if(parcelas <= 1){
            throw new FieldNumberMustBeBigger(" 'parcelas' must be greater than 1x");
        }
        return true;
    }

    // ====== if sinal was filled, 'parcelas' must be filled as well ======
    @Override
    public Boolean sinalAndParcelasMustBeFilledTogether(Double sinal, Integer parcelas){

        if(sinal == null){
            throw new FieldCannotBeNullException(" 'sinal' and 'parcelas' must be filled together");
        }
        return true;
    }

    // ====== all validations passed ======
    @Override
    public Boolean allValidationsAreGood(OrcamentoCreate orcamentoCreate) {

        if(orcamentoCreate.getParcelas() != null){
            this.parcelasIsGreaterThan1(orcamentoCreate.getParcelas());
            this.sinalAndParcelasMustBeFilledTogether(orcamentoCreate.getSinal(), orcamentoCreate.getParcelas());
        }
        return true;
    }
}