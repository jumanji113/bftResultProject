package Pages;

import PageElements.Input;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class PractiseFormPage {

    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";

    private static final String NAME = "Alexey";
    private static final String LAST_NAME = "Yudin";
    private static final String MAIL = "Yudin";
    private static final String PHONE = "89997651255";
    private static final File file = new File("src/test/resources/gorizont.jpg");

    private final Input nameInput = new Input("//input[@id = 'firstName']");
    private final Input lastNameInput = new Input("//input[@id = 'lastName']");
    private final Input emailInput = new Input("//input[@id = 'userEmail']");
    //private final Input nameInput = new Input("//input[@id = 'firstName']");
    //private final Input nameInput = new Input("//input[@id = 'firstName']");



    @Step("Открытие страницы")
    public PractiseFormPage openPage() {
        open(BASE_URL);
        return this;
    }

    @Step("Заполнение данных пользователя")
    public PractiseFormPage setUserData(){
        nameInput.setInputValue(NAME);
        lastNameInput.setInputValue(LAST_NAME);
        emailInput.setInputValue(MAIL);
        return this;
    }

}
