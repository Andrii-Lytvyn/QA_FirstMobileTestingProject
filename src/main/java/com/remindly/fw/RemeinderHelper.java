package com.remindly.fw;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RemeinderHelper extends BaseHelper {
    public RemeinderHelper(AppiumDriver driver) {
        super(driver);
    }


    public void enterTitle(String title) {
        type(By.id("reminder_title"), title);
    }

    public void saveReminder() {
        tap(By.id("save_reminder"));
    }

    public int getTotalReminders() {
        List<WebElement> id = driver.findElements(By.id("thumbnail_image"));
        int idCount = id.size();
        System.out.println("Total reminders quantity: " + idCount);
        return idCount;
    }

    public void selectDate(String period, String month, int number, int index)
    //, String period2, String year)
    {
        tap(By.id("date"));
        swipeToMonth(period, month, number);
        tapOnDate(index);
        tapOnYear();
        // swipeToYear(period2, year);
        tap(By.id("ok"));

    }

    private void swipeToYear(String period2, String year) {
        pause(1000);
        if (!getSelectedYear().equalsIgnoreCase(year)) {
            if (period2.equalsIgnoreCase("future")) {

                swipeUntilNeededYear(year, 0.6, 0.5);

            } else if (period2.equalsIgnoreCase("past")) {

                swipeUntilNeededYear(year, 0.5, 0.6);

            }
        }
        //tap(By.id("month_text_view"));
        tap(By.xpath("//*[@resource-id='com.blanyal.remindly:id/month_text_view'"));
    }

    public String getSelectedYear() {
        //return driver.findElement(By.xpath("//*[@resource-id='com.blanyal.remindly:id/month_text_view'")).getText();
        return driver.findElement(By.id("month_text_view")).getText();
    }


    public void swipeUntilNeededYear(String year, double startPoint, double stopPoint) {
        while (!getSelectedYear().equalsIgnoreCase(year)) {
            moveInElement(By.className("android.widget.ListView"), startPoint, stopPoint);
            getSelectedYear();
        }

    }


    private void tapOnYear() {
        tap(By.id("date_picker_year"));
    }

    private void tapOnDate(int index) {
        List<WebElement> days = driver.findElements(By.className("android.view.View"));
        days.get(index).click();

    }

    private void swipeToMonth(String period, String month, int number) {
        pause(1000);

        if (!getSelectedMonth().equalsIgnoreCase(month)) {
            for (int i = 0; i < number; i++) {
                if (period.equalsIgnoreCase("future")) {
                    swipe(0.7, 0.4);
                } else if (period.equalsIgnoreCase("past")) {
                    swipe(0.5, 0.8);
                }

            }
        }


    }

    public String getSelectedMonth() {
        return driver.findElement(By.id("date_picker_month")).getText();
    }

    public void selectTime(String timeOfDay, int xHour, int yHour, int xMin, int yMin) {
        tap(By.id("time"));
        pause(1000);
        if (timeOfDay.equalsIgnoreCase("am")) {
            tapWithCoordinates(288, 1324);
        } else if (timeOfDay.equalsIgnoreCase("pm")) {
            tapWithCoordinates(795, 1324);
        }
        tapWithCoordinates(xHour, yHour);
        tapWithCoordinates(xMin, yMin);
        tap(By.id("ok"));

    }

    public void tapAndSetInterval(String interval) {
        tap(By.id("RepeatNo"));
        type(By.className("android.widget.EditText"), interval);
        pause(1000);
        tap(By.id("android:id/button1"));


    }

    public void setTypeOfRepetitions(String selectType) {
        swipe(0.8, 0.3);
        tap(By.id("repeat_type_icon"));
        if (selectType.equalsIgnoreCase("Day")) {
            tap(By.xpath("//*[@text='Day']"));
        } else if (selectType.equalsIgnoreCase("Minute")) {
            tap(By.xpath("//*[@text='Minute']"));
        } else if (selectType.equalsIgnoreCase("Hour")) {
            tap(By.xpath("//*[@text='Hour']"));
        } else if (selectType.equalsIgnoreCase("Week")) {
            tap(By.xpath("//*[@text='Week']"));
        } else if (selectType.equalsIgnoreCase("Month")) {
            tap(By.xpath("//*[@text='Month']"));
        }
    }
}