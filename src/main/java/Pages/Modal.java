package Pages;

import PageElements.Td;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class Modal {

    private static final Td studenNameTd = new Td("//tr//td[text()='Student Name']/following-sibling::td");
    private static final Td studentEmail = new Td("//tr//td[text()='Student Email']/following-sibling::td");
    private static final Td studentPhone = new Td("//tr//td[text()='Mobile']/following-sibling::td");
    private static final Td studentBday = new Td("//tr//td[text()='Date of Birth']/following-sibling::td");
    private static final Td studentSubjects = new Td("//tr//td[text()='Subjects']/following-sibling::td");
    private static final Td studentHobbies = new Td("//tr//td[text()='Hobbies']/following-sibling::td");
    private static final Td studentPicture = new Td("//tr//td[text()='Picture']/following-sibling::td");
    private static final Td studentAddress = new Td("//tr//td[text()='Address']/following-sibling::td");
    private static final Td studentStateAndCity = new Td("//tr//td[text()='State and City']/following-sibling::td");

    @Step("Проверка имени и фамилии")
    public Modal assertDataName(String expectedFirstName, String expectedLastName) {
        String studenName = expectedFirstName + " " + expectedLastName;
        String actualStudentName = studenNameTd.getTextTd();
        Assertions.assertEquals(studenName, actualStudentName);
        return this;
    }

    @Step("Проверка телефона и мейла")
    public Modal assertDataPhoneAndEmail(String expectedMail, String expectedPhone){
        Assertions.assertEquals(expectedMail, studentEmail.getTextTd());
        Assertions.assertEquals(expectedPhone, studentPhone.getTextTd());
        return this;
    }

    @Step("Проверка Дня Рождения")
    public Modal assertDbday(String expectedDay, String expectedMonth, String expectedYear){
        String expectedBday = expectedDay + " " + expectedMonth + "," + expectedYear;
        Assertions.assertEquals(expectedBday, studentBday.getTextTd());
        return this;
    }

    @Step("Проверка hobbies и subjects")
    public Modal assertDataHobbiesAndSubjects(String expectedHobbies, String expectedSubject){
        Assertions.assertEquals(expectedHobbies, studentHobbies.getTextTd());
        Assertions.assertEquals(expectedSubject, studentSubjects.getTextTd());
        return this;
    }

    @Step("Проверка hobbies и subjects")
    public Modal assertDataPictureAndAddress(String expectedPicture, String expectedAddress){
        Assertions.assertEquals(expectedPicture, studentPicture.getTextTd());
        Assertions.assertEquals(expectedAddress, studentAddress.getTextTd());
        return this;
    }

    @Step("Проверка State and City")
    public Modal assertDataStateAndCity(String expectedState, String expectedCity){
        String expectedStateAndCityString = expectedState + " " + expectedCity;
        Assertions.assertEquals(expectedStateAndCityString, studentStateAndCity.getTextTd());
        return this;
    }
}
