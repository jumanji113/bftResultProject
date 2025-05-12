package Pages;

import PageElements.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class PractiseFormPage {
    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    private static final String NAME = "Alexey";
    private static final String LAST_NAME = "Yudin";
    private static final String MAIL = "a.yudin@yandex.ru";
    private static final String PHONE = "8999765125";
    private static final String ADDRESS = "Moscow";
    private static final File FILE = new File("src/test/resources/gorizont.jpg");

    private final Input nameInput = new Input("//input[@id='firstName']");
    private final Input lastNameInput = new Input("//input[@id='lastName']");
    private final Input emailInput = new Input("//input[@id='userEmail']");
    private final Input phoneInput = new Input("//input[@id='userNumber']");
    private final Input subjectInput = new Input("//input[@id='subjectsInput']");
    private final Input fileInput = new Input("//input[@id='uploadPicture']");
    private final RadioButton genderRadio = new RadioButton("//div[contains(@class,'custom-radio')]");
    private final Button stateButtonPick = new Button("//div[@id='react-select-3-option-2']");
    private final TextArea textArea = new TextArea("//textarea[@id='currentAddress']");
    private final Button calendar = new Button("//div[@class='react-datepicker__input-container']");
    private final Select selectMonthBday = new Select("//select[@class='react-datepicker__month-select']");
    private final Select selectYearBday = new Select("//select[@class='react-datepicker__year-select']");
    private final Button daysBday = new Button("//div[contains(@aria-label, 'Choose Sunday, May 14th, 1995')]");
    private final Button buttonSelectState =
            new Button("//div[@class=' css-1wy0on6']//div[@class=' css-tlfecz-indicatorContainer'][1]");
    private final Button buttonSelectCity = new Button("//div[text()='Select City']");
    private final Button buttonCityKarnalSelect = new Button("//div[text()='Karnal']");
    private final Button submitButton = new Button("//button[@id='submit']");

    @Step("Открытие страницы")
    public PractiseFormPage openPage() {
        open(BASE_URL);
        return this;
    }

    @Step("Заполнение данных пользователя")
    public PractiseFormPage setUserData() {
        nameInput.setInputValue(NAME);
        lastNameInput.setInputValue(LAST_NAME);
        return this;
    }

    @Step("Заполнение контактных данных пользователя")
    public PractiseFormPage setContactsUser() {
        emailInput.setInputValue(MAIL);
        phoneInput.setInputValue(PHONE);
        return this;
    }

    @Step("Заполнение поля subject")
    public PractiseFormPage setMathSubject() {
        subjectInput
                .scrollTo()
                .setInputValue("Maths").sendConfirmValue();
        return this;
    }

    @Step("Выбор мужского пола")
    public PractiseFormPage setMaleGender() {
        genderRadio.selectByLabel("Male");
        return this;
    }

    @Step("Выбор женского пола")
    public PractiseFormPage setFemaleGender() {
        genderRadio.selectByLabel("Female");
        return this;
    }

    @Step("Выбор даты рождения: месяц {month}, год {year}, день {day}")
    public PractiseFormPage setDateOfBirth(String month, String year, String day) {
        calendar.clickButton();
        selectYearBday.setOption(year);
        selectMonthBday.setOption(month);
        daysBday.clickButton();
        return this;
    }

    @Step("Выбор хобби {hobby}")
    public PractiseFormPage setHobby(String hobby) {
        new Button("//label[text()='" + hobby + "']")
                .scrollToButton()
                .clickButton();
        return this;
    }

    @Step("Загрузка файла {FILE}")
    public PractiseFormPage uploadFileForm() {
        fileInput
                .scrollTo()
                .uploadFile(FILE);
        return this;
    }

    @Step("Ввод адреса")
    public PractiseFormPage setAddress() {
        textArea.setValueWithNewLines(ADDRESS);
        return this;
    }

    @Step("Выбор штата")
    public PractiseFormPage setState() {
        buttonSelectState.clickButton();
        stateButtonPick.clickButton();
        return this;
    }

    @Step("Выбор штата")
    public PractiseFormPage setCity() {
        buttonSelectCity.clickButton();
        buttonCityKarnalSelect.clickButton();
        return this;
    }

    @Step("Нажатие кнопки подтверждения данных")
    public PractiseFormPage submitForm(){
        submitButton.scrollToButton()
                .clickButton();
        return this;
    }

    @Step("Проверка обязательных полей")
    public PractiseFormPage testValidation(){
        String borderColorFirstName = $x("//input[@id='firstName']")
                .scrollTo()
                .shouldBe(Condition.cssValue("border-color","rgb(220, 53, 69)"))
                .getCssValue("border-color");
        String borderColorLastName = $x("//input[@id='lastName']")
                .scrollTo()
                .shouldBe(Condition.cssValue("border-color","rgb(220, 53, 69)"))
                .getCssValue("border-color");
        String borderColorMobile = $x("//input[@id='userNumber']")
                .scrollTo()
                .shouldBe(Condition.cssValue("border-color","rgb(220, 53, 69)"
                ))
                .getCssValue("border-color");
        Assertions.assertEquals("rgb(220, 53, 69)" , borderColorFirstName);
        Assertions.assertEquals("rgb(220, 53, 69)", borderColorLastName);
        Assertions.assertEquals("rgb(220, 53, 69)", borderColorMobile);
        return this;
    }
}