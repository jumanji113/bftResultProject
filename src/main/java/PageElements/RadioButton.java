package PageElements;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RadioButton {
    private final String baseXpath;

    public RadioButton(String baseXpath) {
        this.baseXpath = baseXpath;
    }

    @Step("Выбор радио-кнопки по значению '{value}'")
    public void selectByLabel(String label) {
        $x(baseXpath + "//label[text()='" + label + "']")
                .shouldBe(visible)
                .click();
    }
}