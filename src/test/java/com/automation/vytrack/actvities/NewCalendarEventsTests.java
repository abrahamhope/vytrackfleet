package com.automation.vytrack.actvities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.vytrack.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class NewCalendarEventsTests extends AbstractTestBase {
    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
    /**
     * Test Case :Default options
     *
     */

    @Test
    public void defaultOptionsTest (){
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        Assert.assertEquals(calendarEventsPage.getOwnerName(), calendarEventsPage.getCurrentUserName());
        Assert.assertEquals(calendarEventsPage.getStartDate(), DateTimeUtilities.getCurrentDate("MMM dd, yyyy"));
    }

    @Test
    public void timeDifferenceTest () throws ParseException {


        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();

        String startTime=calendarEventsPage.getStartTime();
        String endTime = calendarEventsPage.getEndTime();

        long actual = DateTimeUtilities.getTimeDifference(startTime,endTime,"h:mm a");
        Assert.assertEquals(actual,1);
    }

    @Test
    public void verifyColumnNamesTest(){
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT", "RECURRENCE", "INVITATION STATUS");
        Assert.assertEquals(calendarEventsPage.getColumnNames(), expected);
    }
}
