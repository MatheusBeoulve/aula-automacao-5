package io.exemplo.automacao.tests;

import io.exemplo.automacao.extensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(WebDriverExtension.class)
public class QuartoWebDriverTest {

    @Test
    public void pesquisarCachorroDeveRetornarDadosSobreSuaEspecie(WebDriver webDriver) {
        webDriver.get("https://www.google.com.br/");

        WebElement barraDePesquisa = webDriver.findElement(By.cssSelector("[name=q]"));

        barraDePesquisa.sendKeys("cachorro");
        barraDePesquisa.submit();

        String titulo = webDriver.findElement(By.cssSelector("div.SzZmKb > div > div > div > div.SPZz6b > h2 > span")).getText();

        String nomeCientifico = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(2) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();
        String expectativaVida = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(3) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();
        String periodoGestacao = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(4) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();
        String altura = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(5) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();
        String horasSono = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(6) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();
        String velocidade = webDriver.findElement(By.cssSelector("#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div:nth-child(7) > div > div > div > span.LrzXr.kno-fv.wHYlTd.z8gr9e")).getText();

        assertAll(() -> assertEquals("Cachorro", titulo),
                () -> assertEquals("Canis lupus familiaris", nomeCientifico),
                () -> assertEquals("10 – 13 anos", expectativaVida),
                () -> assertEquals("58 – 68 dias", periodoGestacao),
                () -> assertEquals("15 – 110 cm (até ao ombro)", altura),
                () -> assertEquals("12 – 14 horas (Adulto)", horasSono),
                () -> assertEquals("Pastor-alemão: 48 km/h, Galgo inglês: 72 km/h", velocidade));
    }

    @Test
    public void pesquisarCachorroDeveRetornarSugestoesDePesquisaSobreCachorros(WebDriver webDriver) {
        webDriver.get("https://www.google.com.br/");

        WebElement barraDePesquisa = webDriver.findElement(By.cssSelector("[name=q]"));

        barraDePesquisa.sendKeys("cachorro");
        barraDePesquisa.submit();

        List<WebElement> sugestoes = webDriver.findElements(By.cssSelector("a.k8XOCe"));

        assertEquals(8, sugestoes.size(), "Quantidade de sugestoes");

        int sugestoesParaCachorro = 0;

        for (WebElement sugestao : sugestoes){
            if(sugestao.getText().contains("cachorro")) {
                sugestoesParaCachorro++;
            }
        }

        assertTrue(sugestoesParaCachorro > 0,
                "Quantidade de sugestoes deve conter pelo menos uma com a palavra cachorro");
    }
}
