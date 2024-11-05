package br.com.example.lgabrieldev.gerador_orcamentos.exceptions.whats_elementos;

public class WhatsElementNotFoundException extends RuntimeException{
    
    //constructors
    public WhatsElementNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
