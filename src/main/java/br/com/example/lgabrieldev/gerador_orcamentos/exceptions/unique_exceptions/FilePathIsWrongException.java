package br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions;

public class FilePathIsWrongException extends RuntimeException{
    
    //constructors
    public FilePathIsWrongException(String errorMessage){
        super(errorMessage);
    }
}
