package io.exemplo.automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.function.Consumer;

public class RegistroSucesso {

    public RegistroSucesso(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#confirmar-dados")
    private WebElement mensagemSucesso;

    public String mensagemConfirmacao() {
        return mensagemSucesso.getText();
    }

    public RegistroSucesso mensagemConfirmacao(Consumer<String> validacao) {

        validacao.accept(mensagemSucesso.getText());

        return this;
    }
}
