package PageElements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Select {
    private SelenideElement select;

    public Select(String xpath) {
        this.select = $x(xpath);
    }

    @Step("Установка значения селектора")
    public Select setOption(String value){
        select.selectOption(value);
        return this;
    }
}
