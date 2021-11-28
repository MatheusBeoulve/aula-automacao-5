package io.exemplo.automacao.tests;


import io.exemplo.automacao.extensions.WebDriverExtension;
import io.exemplo.automacao.pages.RegistroTransacaoVeiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WebDriverExtension.class)
public class QuintoWebDriverTest {

    @Test
    public void primeiroExemplo(WebDriver webDriver) {
        new RegistroTransacaoVeiculo(webDriver)
                .acessarAulaDois()
                .registroParaCarro()
                .preencherFormulario("Pedro Pitanga")
                .enviarDados()
                .mensagemConfirmacao(mensagem -> assertEquals("Obrigado, Pedro Pitanga pelo seu pedido!", mensagem));
    }

    @Test
    public void quandoCarroForAsseguradoPossoEscolherSeOSeguroJaFoiUsado(WebDriver webDriver) {
        new RegistroTransacaoVeiculo(webDriver)
                .acessarAulaDois()
                .registroParaCarro()
                .usouSeguro(Assertions::assertFalse)
                .checarCarroAssegurado()
                .usouSeguro(Assertions::assertTrue);
    }
}
