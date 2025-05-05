import Pages.PractiseFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UiTest extends BaseTest {

    private static final String MONTH = "May";
    private static final String YEAR = "1995";
    private static final String DAY = "14";

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
    }

}