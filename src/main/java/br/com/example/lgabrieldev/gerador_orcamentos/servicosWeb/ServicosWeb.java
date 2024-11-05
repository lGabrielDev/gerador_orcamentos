package br.com.example.lgabrieldev.gerador_orcamentos.servicosWeb;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import br.com.example.lgabrieldev.gerador_orcamentos.servicosWeb.whatsapp.WhatsAppManipulacao;

@Component
public class ServicosWeb {
    
    //attributes
    private NavegadorConfigs navegadorConfigs;
    private WhatsAppManipulacao whatsAppManipulacao;
    private WebDriver navegador;

    @Value("${PHONE_NUMBER}")
    private String phoneNumber;

    //constructors
    public ServicosWeb(NavegadorConfigs navegadorConfigs, WhatsAppManipulacao whatsAppManipulacao){
        this.navegadorConfigs = navegadorConfigs;
        this.whatsAppManipulacao = whatsAppManipulacao;
    }

    // =========== WhatsApp ===========
    public void enviarPdfWhats(String pdfPath) {
        String urlWhatsApp = "https://web.whatsapp.com/send/?phone=55" + this.phoneNumber;

        //criamos um navegador
        this.navegador = this.navegadorConfigs.gerarNavegador();
        
        navegador.get(urlWhatsApp);
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(15));

        this.whatsAppManipulacao.sendPdf(navegador, wait, pdfPath);
    }

    // =========== Google Drive ===========
    public void uploadPdfDrive(String pdfPath) {
        String urlGoogleDrive = "https://drive.google.com/";

        //criamos um navegador
        //this.navegador.get(urlGoogleDrive);

        WebDriver navegador = this.navegadorConfigs.gerarNavegador();
        navegador.get(urlGoogleDrive);
        //WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(15));
    }
}