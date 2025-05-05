package PageElements;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TextArea {
    private SelenideElement textArea;

    public TextArea(String textArea) {
        this.textArea = $x(textArea);
    }

    @Step("Установка значения в textarea (с поддержкой переносов строк)")
    public TextArea setValueWithNewLines(String text) {
        textArea.shouldBe(visible).setValue(text);
        return this;
    }

    @Step("Проверка, что textarea содержит текст с переносами строк")
    public TextArea shouldHaveTextWithNewLines(String expectedText) {
        textArea.shouldHave(value(expectedText));
        return this;
    }

}
