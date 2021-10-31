package io.exemplo.automacao.extensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class WebDriverExtension extends TypeBasedParameterResolver<WebDriver>
        implements AfterTestExecutionCallback {

    private static final String KEY = "WebDriver";
    private static final Boolean DONT_USE_LOCAL_DRIVER = Boolean.getBoolean("dont-use-local-driver");
    private static final Integer DEFAULT_IMPLICITLY_TIMEOUT = 10;

    public WebDriver resolveParameter(ParameterContext parameterContext,
                                      ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return (WebDriver) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
                .getOrComputeIfAbsent(KEY, ignore -> DONT_USE_LOCAL_DRIVER ? new ChromeDriver() : instantiateWebDriver());
    }

    public void afterTestExecution(ExtensionContext context) {
        Optional.of(context.getStore(ExtensionContext.Namespace.GLOBAL).get(KEY, WebDriver.class))
                .ifPresent(WebDriver::quit);
    }

    private WebDriver instantiateWebDriver() {

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .usingDriverExecutable(loadFileFromClasspath("chromedriver.exe"))
                .build();

        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--no-first-run")
                .setBinary(loadFileFromClasspath("chrome.exe"));

        WebDriver webDriver = new ChromeDriver(chromeDriverService, chromeOptions);

        webDriver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_TIMEOUT, TimeUnit.SECONDS);

        return webDriver;
    }

    private File loadFileFromClasspath(String fileName) {
        return new File(Objects.requireNonNull(WebDriverExtension.class.getClassLoader().getResource(fileName)).getFile());
    }
}
