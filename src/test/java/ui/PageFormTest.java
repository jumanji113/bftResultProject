package ui;

import Pages.Modal;
import Pages.PractiseFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PageFormTest extends BaseTest {

    private static final String MONTH = "May";
    private static final String YEAR = "1995";
    private static final String DAY = "14";
    private static final String NAME = "Alexey";
    private static final String LAST_NAME = "Yudin";
    private static final String MAIL = "a.yudin@yandex.ru";
    private static final String PHONE = "89997651255";
    private static final String ADDRESS = "Moscow";

    @ParameterizedTest(name = "Выбор хобби: {0}")
    @DisplayName("Проверка заполнения формы")
    @ValueSource(strings = {"Sports", "Reading", "Music"})
    public void testSelectHobby(String hobby) {
        new PractiseFormPage()
                .openPage()
                .setUserData()
                .setContactsUser()
                .setMaleGender()
                .setDateOfBirth(MONTH, YEAR, DAY)
                .setMathSubject()
                .setHobby(hobby)
                .uploadFileForm()
                .setAddress()
                .setState();
        new Modal()
                .assertDataName(NAME, LAST_NAME)
                .assertDataPhoneAndEmail(MAIL, PHONE)
                .assertDbday(DAY, MONTH, YEAR)
                .
    }

}