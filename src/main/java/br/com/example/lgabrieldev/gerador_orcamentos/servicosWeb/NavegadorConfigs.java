package br.com.example.lgabrieldev.gerador_orcamentos.servicosWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NavegadorConfigs {
    
    //attributes
    @Value("${YOUR_CHROME_PROFILE}")
    private String CHROME_PROFILE_PATH;

    public WebDriver gerarNavegador(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + CHROME_PROFILE_PATH);
        return new ChromeDriver(options);
    }
}