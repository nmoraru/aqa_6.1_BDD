package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardTransactionButton =
            $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondCardTransactionButton =
            $("[data-test-id = '0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement refresh = $(byText("Обновить"));

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransactionPage firstCardInfo() {
        firstCardTransactionButton.click();
        return new TransactionPage();
    }

    public TransactionPage secondCardInfo() {
        secondCardTransactionButton.click();
        return new TransactionPage();
    }

    public DashboardPage refreshPageInfo() {
        refresh.click();
        return new DashboardPage();
    }

    public void newBalanceUnder() {
        $(byText("15000")).waitUntil(Condition.visible, 5000);
        $(byText("5000")).waitUntil(Condition.visible, 5000);
    }

    public void newBalanceAll() {
        $(byText("20000")).waitUntil(Condition.visible, 5000);
        $(byText("0")).waitUntil(Condition.visible, 5000);
    }

    public void newBalanceWithCent() {
        $(byText("10000,01")).waitUntil(Condition.visible, 5000);
        $(byText("9999,99")).waitUntil(Condition.visible, 5000);
    }

    public void newBalanceRollback() {
        $(byText("10000")).waitUntil(Condition.visible, 5000);
    }

}
