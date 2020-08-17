package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionPage {
    private SelenideElement titleText = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id = from] input");
    private SelenideElement doneButton = $(byText("Пополнить"));
    private SelenideElement cancelButton = $(byText("Отмена"));

    public TransactionPage() {
        titleText.shouldBe(visible);
    }

    public void clearAmountAndFromFields() {
        amountField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        fromField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }

    public DashboardPage cancelTransaction(String from, String amount) {
        clearAmountAndFromFields();
        amountField.setValue(amount);
        fromField.setValue(from);
        cancelButton.click();
        return new DashboardPage();
    }

    public DashboardPage transactionAnotherCards(String from, String amount) {
        clearAmountAndFromFields();
        amountField.setValue(amount);
        fromField.setValue(from);
        doneButton.click();
        return new DashboardPage();
    }

    public DashboardPage transactionRollback(String from, String amount) {
        clearAmountAndFromFields();
        amountField.setValue(amount);
        fromField.setValue(from);
        doneButton.click();
        return new DashboardPage();
    }

    public void transactionOverMaxBalance(String from, String amount) {
        clearAmountAndFromFields();
        amountField.setValue(amount);
        fromField.setValue(from);
        doneButton.click();
        $(byText("Сумма перевода не может превышать суммы на счете.")).waitUntil(Condition.visible, 5000);
    }

}
