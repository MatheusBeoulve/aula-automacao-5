package io.exemplo.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

public class RegistroTransacaoMoto {

    private final WebDriver webDriver;

    public RegistroTransacaoMoto(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RegistroTransacaoMoto usouSeguro(Consumer<Boolean> validacao) {

        validacao.accept(webDriver.findElement(By.id("usou-seguro-moto")).isEnabled());

        return this;
    }

    public RegistroTransacaoMoto checarMotoAssegurada() {

        webDriver.findElement(By.id("moto-assegurada")).click();

        return this;
    }
}
