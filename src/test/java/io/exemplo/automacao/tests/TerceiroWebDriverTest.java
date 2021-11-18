package io.exemplo.automacao.tests;

import io.exemplo.automacao.extensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WebDriverExtension.class)
public class TerceiroWebDriverTest {

    @Test
    public void terceiraAula(WebDriver webDriver) {
        webDriver.get("https://matheusbeoulve.github.io/aulas/aula1/desafio/");

        webDriver.findElement(By.cssSelector("#nome")).sendKeys("Juliana");
        webDriver.findElement(By.cssSelector("#sobrenome")).sendKeys("Menezes");
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("juliana.menezes@aitec.pt");
        webDriver.findElement(By.cssSelector("#endereco")).sendKeys("Avenida testando AUTO");
        webDriver.findElement(By.cssSelector("#telefone")).sendKeys("11970706861");

        webDriver.findElement(By.xpath("//input[@type='submit']")).click();

        String mensagem =  webDriver.findElement(By.cssSelector ("*")).getText();
        assertEquals("Obrigado pela sua inscricao!", mensagem);
    }
}
