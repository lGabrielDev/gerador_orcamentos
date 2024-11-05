package br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions;

public class FileIsOpenException extends RuntimeException{

    //constructors
    public FileIsOpenException(String errorMessage){
       super(errorMessage);
   }
}
