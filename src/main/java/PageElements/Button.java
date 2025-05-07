package PageElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Button {

    private SelenideElement button;

    public Button(String xpath) {
        this.button = $x(xpath);
    }

    @Step("Кликаем на кнопку поиска")
    public Button clickButton() {
        button.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Скроллим до элемента")
    public Button scrollToButton() {
        button.scrollTo();
        return this;
    }

    @Step("Проверка, что кнопка активна")
    public boolean isEnabled() {
        return button.is(Condition.enabled);
    }
}
