package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

public class CardTest {

    @Test
    void shouldValidUserAuth() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.validVerify();
    }

    @Test
    void shouldRefreshPageInfo() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        dashboardPage.refreshPageInfo();
    }

    @Test
    void shouldCancelTransaction() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        val transactionPage = dashboardPage.firstCardInfo();
        transactionPage.cancelTransaction();
        dashboardPage.newBalanceRollback();
    }

    @Test
    void shouldTransactionUnderBalanceAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        val transactionPage = dashboardPage.firstCardInfo();
        transactionPage.transactionUnderBalanceAnotherCards();
        dashboardPage.newBalanceUnder();

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionUnderBalanceAnotherCardsRollback();
        dashboardPage.newBalanceRollback();
    }

    @Test
    void shouldTransactionAllBalanceAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        val transactionPage = dashboardPage.firstCardInfo();
        transactionPage.transactionAllBalanceAnotherCards();
        dashboardPage.newBalanceAll();

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionAllBalanceAnotherCardsRollback();
        dashboardPage.newBalanceRollback();
    }

    @Test
    void shouldTransactionOverMaxAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        val transactionPage = dashboardPage.firstCardInfo();
        transactionPage.transactionOverMaxBalance();
    }

    @Test
    void shouldTransactionWithOneCentAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();
        val transactionPage = dashboardPage.firstCardInfo();
        transactionPage.transactionWithCentAnotherCards();
        dashboardPage.newBalanceWithCent();

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionWithCentAnotherCardsRollback();
        dashboardPage.newBalanceRollback();
    }

    @Test
    void shouldInvalidLoginUser() {
        val loginPage = new LoginPage();
        loginPage.invalidLoginUser();
    }

    @Test
    void shouldInvalidPasswordUser() {
        val loginPage = new LoginPage();
        loginPage.invalidPasswordUser();
    }

    @Test
    void shouldInvalidVerify() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.invalidVerify();
    }

    @Test
    void shouldBlockedVerify() throws InterruptedException {
        //shouldInvalidVerify(); -- 3-я невалидная проверка для блокировки ввода кода проходит в тесте shouldInvalidVerify()
        shouldInvalidVerify();
        shouldInvalidVerify();

        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.blockedVerify();
        Thread.sleep(5000);
    }

}
