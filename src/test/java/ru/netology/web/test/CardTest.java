package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

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

        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val amount = "5000";

        transactionPage.cancelTransaction("5559 0000 0000 0002", amount);

        val expectedFirstCardBalance = firstCardStartBalance + Integer.parseInt(amount);
        val expectedSecondCardBalance = secondCardStartBalance - Integer.parseInt(amount);
        val actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        val actualSecondCardBalance = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
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


    @Test
    void shouldTransactionUnderBalanceAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();

        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();

        val transactionPage = dashboardPage.firstCardInfo();

        val amount = "5000";
        transactionPage.transactionAnotherCards("5559 0000 0000 0002", amount);

        val expectedFirstCardBalance = firstCardStartBalance + Integer.parseInt(amount);
        val expectedSecondCardBalance = secondCardStartBalance - Integer.parseInt(amount);
        val actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        val actualSecondCardBalance = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionRollback("5559 0000 0000 0001", amount);

        assertEquals(firstCardStartBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardStartBalance, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransactionAllBalanceAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();

        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();

        val transactionPage = dashboardPage.firstCardInfo();

        String amount = String.valueOf(secondCardStartBalance);

        transactionPage.transactionAnotherCards("5559 0000 0000 0002", amount);

        val expectedFirstCardBalance = firstCardStartBalance + Integer.parseInt(amount);
        val expectedSecondCardBalance = secondCardStartBalance - Integer.parseInt(amount);
        val actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        val actualSecondCardBalance = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionRollback("5559 0000 0000 0001", amount);

        assertEquals(firstCardStartBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardStartBalance, dashboardPage.getSecondCardBalance());
    }


    @Test
    void shouldTransactionOverMaxAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();

        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();

        val transactionPage = dashboardPage.firstCardInfo();

        String amount = String.valueOf(secondCardStartBalance + 5000);

        transactionPage.transactionOverMaxBalance("5559 0000 0000 0002", amount);

        val expectedFirstCardBalance = firstCardStartBalance + Integer.parseInt(amount);
        val expectedSecondCardBalance = secondCardStartBalance - Integer.parseInt(amount);
        val actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        val actualSecondCardBalance = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionRollback("5559 0000 0000 0001", amount);

        assertEquals(firstCardStartBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardStartBalance, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransactionWithOneCentAnotherCards() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        val dashboardPage = verificationPage.validVerify();

        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();

        val transactionPage = dashboardPage.firstCardInfo();

        String amount = "0,01";
        transactionPage.transactionAnotherCards("5559 0000 0000 0002", amount);

        val expectedFirstCardBalance = firstCardStartBalance + Integer.parseInt(amount);
        val expectedSecondCardBalance = secondCardStartBalance - Integer.parseInt(amount);
        val actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        val actualSecondCardBalance = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);

        val transactionPage2 = dashboardPage.secondCardInfo();
        transactionPage2.transactionRollback("5559 0000 0000 0001", amount);

        assertEquals(firstCardStartBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardStartBalance, dashboardPage.getSecondCardBalance());
    }

}
