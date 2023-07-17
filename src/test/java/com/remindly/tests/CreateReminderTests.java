package com.remindly.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateReminderTests extends TestBase {
    @Test
    public void addReminderWithDefaultData() {
        int quantityBefore = app.getReminder().getTotalReminders();
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterTitle("Test");
        app.getReminder().saveReminder();
        int quantityAfterAdd = app.getReminder().getTotalReminders();
        Assert.assertEquals(quantityAfterAdd, quantityBefore + 1);

    }

    @Test
    public void addReminderPositiveTest() {
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterTitle("1 September");

        app.getReminder().selectDate("future", "SEP", 3, 0);

        app.getReminder().selectTime("am", 405, 690, 542, 1191);
        app.getReminder().tapAndSetInterval("3");
        app.getReminder().setTypeOfRepetitions("Month");
        app.getReminder().saveReminder();
    }
}
//xpath = //*[@text='искомый текст']
// xpath = //*[@text='" +переменная+ "'] через канкатинаци.