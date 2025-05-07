package ui;

import Pages.Modal;
import Pages.PractiseFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PageFormTest extends BaseTest {

    private static final String MONTH = "May";
    private static final String YEAR = "1995";
    private static final String DAY = "14";
    private static final String NAME = "Alexey";
    private static final String LAST_NAME = "Yudin";
    private static final String MAIL = "a.yudin@yandex.ru";
    private static final String PHONE = "8999765125";
    private static final String CURRENT_ADDRESS = "Moscow";
    private static final String HOBBIES1 = "Sports";
    private static final String SUBJECT = "Maths";
    private static final String PICTURE = "gorizont.jpg";
    private static final String STATE = "Haryana";
    private static final String CITY = "Karnal";


    @ParameterizedTest(name = "Выбор хобби: {0}")
    @DisplayName("Проверка заполнения формы и проверка данных модального окна")
    @ValueSource(strings = {"Sports"})
    public void testPositiveFormPage(String hobby) {
        new PractiseFormPage()
                .openPage()
                .setUserData()
                .setMaleGender()
                .setContactsUser()
                .setDateOfBirth(MONTH, YEAR, DAY)
                .setMathSubject()
                .setHobby(hobby)
                .uploadFileForm()
                .setAddress()
                .setState()
                .setCity()
                .submitForm();
        new Modal()
                .assertDataName(NAME, LAST_NAME)
                .assertDataPhoneAndEmail(MAIL, PHONE)
                .assertDbday(DAY, MONTH, YEAR)
                .assertDataHobbiesAndSubjects(HOBBIES1, SUBJECT)
                .assertDataPictureAndAddress(PICTURE, CURRENT_ADDRESS)
                .assertDataStateAndCity(STATE, CITY);
    }

    @Test
    @DisplayName("Тест проверка валидации формы")
    public void testValidation() {
        new PractiseFormPage()
                .openPage()
                .setFemaleGender()
                .submitForm()
                .testValidation();
    }

}