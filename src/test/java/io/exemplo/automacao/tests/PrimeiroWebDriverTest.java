package io.exemplo.automacao.tests;

import io.exemplo.automacao.extensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WebDriverExtension.class)
public class PrimeiroWebDriverTest {

    @Test
    public void abrirYoutubeValidandoTituloDoVideo(WebDriver webDriver) {
        webDriver.get("https://matheusbeoulve.github.io/aulas/aula1/");

        webDriver.findElement(By.cssSelector("#criar-conta")).click();

        webDriver.findElement(By.cssSelector("#usuario")).sendKeys("Nome");
        webDriver.findElement(By.cssSelector("#senha")).sendKeys("Senha123");

        webDriver.findElement(By.cssSelector("#enviar")).click();

        String mensagem = webDriver.findElement(By.cssSelector("#sucesso")).getText();

        assertEquals("Sua conta foi criada com sucesso!", mensagem);
    }
}
