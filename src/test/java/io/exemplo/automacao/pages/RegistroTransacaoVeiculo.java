package io.exemplo.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistroTransacaoVeiculo {

    private final WebDriver webDriver;

    public RegistroTransacaoVeiculo(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RegistroTransacaoVeiculo acessarAulaDois() {

        webDriver.get("https://matheusbeoulve.github.io/aulas/aula2/");

        return this;
    }

    public RegistroTransacaoCarro registroParaCarro() {

        webDriver.findElement(By.cssSelector("[href='carro.html']")).click();

        return new RegistroTransacaoCarro(webDriver);
    }
}
