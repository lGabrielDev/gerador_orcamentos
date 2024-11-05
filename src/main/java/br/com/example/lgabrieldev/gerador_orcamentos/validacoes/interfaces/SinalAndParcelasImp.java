package br.com.example.lgabrieldev.gerador_orcamentos.validacoes.interfaces;

public interface SinalAndParcelasImp {
    
    public Boolean sinalAndParcelasMustBeFilledTogether(Double sinal, Integer parcelas);
}