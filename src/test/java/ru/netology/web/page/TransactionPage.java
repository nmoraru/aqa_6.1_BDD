package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement titleText = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id = from] input");
    private SelenideElement done = $(byText("Пополнить"));
    private SelenideElement cancel = $(byText("Отмена"));


    public TransactionPage() {
        titleText.shouldBe(visible);
    }

    public void clear() {
        amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        from.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }

    public DashboardPage cancelTransaction() {
        clear();
        amount.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCards().getAmount());
        from.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCards().getFrom());
        cancel.click();
        return new DashboardPage();
    }

    public DashboardPage transactionUnderBalanceAnotherCards() {
        clear();
        amount.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCards().getAmount());
        from.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCards().getFrom());
        done.click();
        return new DashboardPage();
    }

    public DashboardPage transactionUnderBalanceAnotherCardsRollback() {
        clear();
        amount.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCardsRollback().getAmount());
        from.setValue(DataGenerator.generateTransactionUnderBalanceAnotherCardsRollback().getFrom());
        done.click();
        return new DashboardPage();
    }

    public DashboardPage transactionAllBalanceAnotherCards() {
        clear();
        amount.setValue(DataGenerator.generateTransactionAllBalanceAnotherCards().getAmount());
        from.setValue(DataGenerator.generateTransactionAllBalanceAnotherCards().getFrom());
        done.click();
        return new DashboardPage();
    }

    public DashboardPage transactionAllBalanceAnotherCardsRollback() {
        clear();
        amount.setValue(DataGenerator.generateTransactionAllBalanceAnotherCardsRollback().getAmount());
        from.setValue(DataGenerator.generateTransactionAllBalanceAnotherCardsRollback().getFrom());
        done.click();
        return new DashboardPage();
    }

    public void transactionOverMaxBalance() {
        clear();
        amount.setValue(DataGenerator.generateTransactionOverBalanceAnotherCards().getAmount());
        from.setValue(DataGenerator.generateTransactionOverBalanceAnotherCards().getFrom());
        done.click();
        $(byText("Сумма перевода не может превышать суммы на счете.")).waitUntil(Condition.visible, 5000);
    }

    public DashboardPage transactionWithCentAnotherCards() {
        clear();
        amount.setValue(DataGenerator.generateTransactionWithCentAnotherCards().getAmount());
        from.setValue(DataGenerator.generateTransactionWithCentAnotherCards().getFrom());
        done.click();
        return new DashboardPage();
    }

    public DashboardPage transactionWithCentAnotherCardsRollback() {
        clear();
        amount.setValue(DataGenerator.generateTransactionWithCentAnotherCardsRollback().getAmount());
        from.setValue(DataGenerator.generateTransactionWithCentAnotherCardsRollback().getFrom());
        done.click();
        return new DashboardPage();
    }

}
