package br.com.example.lgabrieldev.gerador_orcamentos.servicosWeb.whatsapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.unique_exceptions.FilePathIsWrongException;
import br.com.example.lgabrieldev.gerador_orcamentos.exceptions.whats_elementos.WhatsElementNotFoundException;

@Component
public class WhatsAppManipulacao {
    
    //attributes
    private final String DIV_PAINEL_CONTATOS = "pane-side"; //by #ID
    private final String PLUS_BUTTON = "//*[@data-icon='plus']"; //by XPATH
    private final String DOCUMENT_INPUT = "//*[@id='main']/footer/div[1]/div/span/div/div[1]/div[2]/div/span/div/ul/div/div[1]/li/div/input"; //by XPATH OU "input[@accept='*']" 
    private final String SEND_BUTTON = "//*[@id='app']/div/div[3]/div[2]/div[2]/span/div/div/div/div[2]/div/div[2]/div[2]/div/div/span"; //by XPATH

    //verificar se o QR_CODE ja foi lido
    public Boolean qrCodeLido(WebDriver navegador, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(this.DIV_PAINEL_CONTATOS)));
        }
        catch(Exception e){
            throw new WhatsElementNotFoundException("DIV_PAINEL_CONTATOS not found. xPath is wrong!");
        }
        return true;
    }
    
    //clicamos no + pra abrir os tipos de envio
    //assim, liberamos outras div escondidas:
    //input para enviar docs no geral
    //input para enviar images
    //input para enviar audio
    //etc...
    public void clickPlusButton(WebDriver navegador, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.PLUS_BUTTON)));
            WebElement botaoPlus = navegador.findElement(By.xpath(this.PLUS_BUTTON));
            Thread.sleep(2000);
            botaoPlus.click();
        }
        catch(Exception e){
            throw new WhatsElementNotFoundException("Plus button not found. xPath is wrong!");
        }
    }

    //click send button
    public void clickSendButton(WebDriver navegador, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.SEND_BUTTON)));

            WebElement sendButton = navegador.findElement(By.xpath(this.SEND_BUTTON));
            Thread.sleep(1500);
            sendButton.click();
            Thread.sleep(3000);
        }
        catch(Exception e){
            throw new WhatsElementNotFoundException("Send button element not found. xPath is wrong!");
        }
    }

    //enviar documento pdf
    public void sendPdf(WebDriver navegador, WebDriverWait wait, String pdfPath){

        this.qrCodeLido(navegador, wait);
        this.clickPlusButton(navegador, wait);

        try{
            //por ser um elemento 'input' e nao aparecer visualmente, devemos usar o 'presenceOfElementLocated'
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.DOCUMENT_INPUT)));
            Thread.sleep(2000);
        }
        catch(Exception e){
            throw new WhatsElementNotFoundException("Input element not found. xPath is wrong!");
        }
        
        try{
            WebElement input = navegador.findElement(By.xpath(this.DOCUMENT_INPUT));    
            input.sendKeys(pdfPath);
            Thread.sleep(2000);
        }
        catch(Exception e){
            throw new FilePathIsWrongException(pdfPath + " not found.");
        }

        this.clickSendButton(navegador, wait);
    }
}