package io.exemplo.automacao.tests;


import io.exemplo.automacao.extensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(WebDriverExtension.class)
public class SegundoWebDriverTest {

    @Test
    public void primeiroExemplo(WebDriver webDriver) {
        String nomeEmissor = "Pedro Pitanga";

        webDriver.get("https://matheusbeoulve.github.io/aulas/aula2/");

        webDriver.findElement(By.cssSelector("[href='carro.html']")).click();

        WebElement tipoTransacao = webDriver.findElement(By.cssSelector("[name='tipo-transacao']"));

        new Select(tipoTransacao).selectByVisibleText("Troca");

        webDriver.findElement(By.cssSelector("[name='valor-veiculo']")).sendKeys("80000");
        webDriver.findElement(By.cssSelector("[name='nome-emissor']")).sendKeys(nomeEmissor);
        webDriver.findElement(By.cssSelector("[name='nome-beneficiario']")).sendKeys("Josefa Silene");
        webDriver.findElement(By.cssSelector("[name='ano-carro']")).sendKeys("2020");

        webDriver.findElement(By.cssSelector("#carro-assegurado")).click();
        webDriver.findElement(By.cssSelector("#usou-seguro-carro")).click();

        webDriver.findElement(By.cssSelector("[type='submit']")).click();

        String mensagem = webDriver.findElement(By.cssSelector("#confirmar-dados")).getText();

        assertEquals("Obrigado, " + nomeEmissor + " pelo seu pedido!", mensagem);

        new Actions(webDriver).pause(Duration.ofSeconds(5)).perform();
    }

    @Test
    public void quandoCarroForAsseguradoPossoEscolherSeOSeguroJaFoiUsado(WebDriver webDriver) {
        webDriver.get("https://matheusbeoulve.github.io/aulas/aula2/");

        webDriver.findElement(By.cssSelector("[href='carro.html']")).click();

        WebElement carroAssegurado = webDriver.findElement(By.cssSelector("#carro-assegurado"));
        WebElement usouSeguro = webDriver.findElement(By.cssSelector("#usou-seguro-carro"));

        assertFalse(usouSeguro.isEnabled());

        carroAssegurado.click();

        assertTrue(usouSeguro.isEnabled());
    }
}
