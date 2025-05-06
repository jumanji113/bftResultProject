package PageElements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Td {
    private SelenideElement td;

    public Td(String xpath) {
        this.td = $x(xpath);
    }

    @Step("Получение текста")
    public String getTextTd() {
        return td.shouldHave(visible).getText();
    }
}
