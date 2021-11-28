package io.exemplo.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Consumer;

public class RegistroTransacaoCarro {

    private final WebDriver webDriver;

    public RegistroTransacaoCarro(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RegistroTransacaoCarro preencherFormulario(String nomeEmissor) {

        WebElement tipoTransacao = webDriver.findElement(By.cssSelector("[name='tipo-transacao']"));

        new Select(tipoTransacao).selectByVisibleText("Troca");

        webDriver.findElement(By.cssSelector("[name='valor-veiculo']")).sendKeys("80000");
        webDriver.findElement(By.cssSelector("[name='nome-emissor']")).sendKeys(nomeEmissor);
        webDriver.findElement(By.cssSelector("[name='nome-beneficiario']")).sendKeys("Josefa Silene");
        webDriver.findElement(By.cssSelector("[name='ano-carro']")).sendKeys("2020");

        webDriver.findElement(By.cssSelector("#carro-assegurado")).click();
        webDriver.findElement(By.cssSelector("#usou-seguro-carro")).click();

        return this;
    }

    public RegistroSucesso enviarDados() {

        webDriver.findElement(By.cssSelector("[type='submit']")).click();

        return new RegistroSucesso(webDriver);
    }

    public RegistroTransacaoCarro usouSeguro(Consumer<Boolean> validacao) {

        validacao.accept(webDriver.findElement(By.cssSelector("#usou-seguro-carro")).isEnabled());

        return this;
    }

    public RegistroTransacaoCarro checarCarroAssegurado() {

        webDriver.findElement(By.cssSelector("#carro-assegurado")).click();

        return this;
    }
}
