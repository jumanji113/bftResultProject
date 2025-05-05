package PageElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Input {

    private SelenideElement input;

    public Input(String xpath) {
        this.input = $x(xpath);
    }

    @Step("Очистка инпута, проверка, что ипнут пуст и  отправка значение в input {value}")
    public Input setInputValue(String value) {
        input.shouldBe(Condition.visible).clear();
        input.shouldHave(Condition.empty);
        input.shouldBe(Condition.visible).setValue(value);
        return this;
    }

    @Step("Проверка, что input имеет атрибут {attribute} со значением {value}")
    public Input shouldHaveAttribute(String attribute, String value) {
        input.shouldHave(Condition.attribute(attribute, value));
        return this;
    }
}
