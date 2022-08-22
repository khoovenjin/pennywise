package com.budgettracking.pennywise.utilities;

import java.util.Date;

public class DateManager {

    private Date mDateFrom;
    private Date mDateTo;

    private static DateManager dateManager = new DateManager();

    public static DateManager getInstance() {
        return dateManager;
    }

    private DateManager() {
        mDateFrom = DateUtilities.getFirstDateOfCurrentMonth();
        mDateTo = DateUtilities.getLastDateOfCurrentMonth();
    }




    public void setDateTo(Date mDateTo) {
        this.mDateTo = mDateTo;
    }

    public Date getDateFrom() {
        return mDateFrom;
    }

    public void setDateFrom(Date mDateFrom) {
        this.mDateFrom = mDateFrom;
    }

    public Date getDateTo() {
        return mDateTo;
    }



}
