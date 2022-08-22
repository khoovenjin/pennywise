package com.budgettracking.pennywise.entities;


import com.budgettracking.pennywise.interfaces.IDateMode;
import com.budgettracking.pennywise.interfaces.IExpensesType;
import com.budgettracking.pennywise.utilities.DateUtilities;
import com.budgettracking.pennywise.utilities.RealmManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
//TO SET EXPENSES
public class Expense extends RealmObject { //Used Realm as in-app data store

    @PrimaryKey
    private String id; //ID of expense

    private String description; //Desc of expense
    private Date date; //Date of expense
    private @IExpensesType int type; //Type of expense
    private Category category; //Category of expense
    private float total; //Total of expense

    public Expense() {
    }

    //Main constructor
    public Expense(String description, Date date, @IExpensesType int type, Category category, float total) {
        this.description = description;
        this.date = date;
        this.type = type;
        this.category = category;
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static float getTotalExpensesByDateMode(@IDateMode int dateMode){
        Date dateFrom;
        Date dateTo;
        switch (dateMode) {
            case IDateMode.MODE_TODAY:
                dateFrom = DateUtilities.getToday();
                dateTo = DateUtilities.getTomorrowDate();
                break;
            case IDateMode.MODE_WEEK:
                dateFrom = DateUtilities.getFirstDateOfCurrentWeek();
                dateTo = DateUtilities.getLastDateOfCurrentWeek();
                break;
            case IDateMode.MODE_MONTH:
                dateFrom = DateUtilities.getFirstDateOfCurrentMonth();
                dateTo = DateUtilities.getLastDateOfCurrentMonth();
                break;
            default:
                dateFrom = new Date();
                dateTo = new Date();
        }
        RealmResults<Expense> totalExpense = getExpensesList(dateFrom, dateTo, IExpensesType.MODE_EXPENSES, null);
        RealmResults<Expense> totalIncome = getExpensesList(dateFrom, dateTo, IExpensesType.MODE_INCOME, null);
        return totalExpense.sum("total").floatValue() - totalIncome.sum("total").floatValue();
    }

    public static List<Expense> getTodayExpenses() {
        Date today = DateUtilities.getToday();
        Date tomorrow = DateUtilities.getTomorrowDate();
        return getExpensesList(today, tomorrow, null, null);
    }

    public static List<Expense> getWeekExpenses() {
        Date startWeek = DateUtilities.getFirstDateOfCurrentWeek();
        Date endWeek = DateUtilities.getLastDateOfCurrentWeek();
        return getExpensesList(startWeek, endWeek, null, null);
    }

    public static List<Expense> getWeekExpensesByCategory(Expense expense) {
        Date startWeek = DateUtilities.getFirstDateOfCurrentWeek();
        Date endWeek = DateUtilities.getLastDateOfCurrentWeek();
        return getExpensesList(startWeek, endWeek, null, expense.getCategory());
    }

    public static List<Expense> getMonthExpenses() {
        Date startMonth = DateUtilities.getFirstDateOfCurrentMonth();
        Date endMonth = DateUtilities.getLastDateOfCurrentMonth();
        return getExpensesList(startMonth, endMonth, null, null);
    }

    public static float getCategoryTotalByDate(Date date, Category category) {
        RealmResults<Expense> totalExpense = getExpensesList(date, DateUtilities.addDaysToDate(date, 1), IExpensesType.MODE_EXPENSES, category);
        return totalExpense.sum("total").floatValue();
    }

    public static float getCategoryTotalByDate(Date fromDate, Date toDate, Category category) {
        RealmResults<Expense> totalExpense = getExpensesList(fromDate, DateUtilities.addDaysToDate(toDate, 1), IExpensesType.MODE_EXPENSES, category);
        return totalExpense.sum("total").floatValue();
    }

    public static RealmResults<Expense> getExpensesPerCategory(Category category) {
        return RealmManager.getInstance().getRealmInstance().where(Expense.class).equalTo("category.id", category.getId()).findAll();
    }

    public static RealmResults<Expense> getExpensesList(Date dateFrom, Date dateTo, @IExpensesType Integer type, Category category) {
        RealmQuery<Expense> realmQuery = RealmManager.getInstance().getRealmInstance()
                .where(Expense.class);
        if (dateTo != null) {
            realmQuery.between("date", dateFrom, dateTo);
        } else {
            realmQuery.equalTo("date", dateFrom);
        }
        if (category != null) realmQuery.equalTo("category.id", category.getId());
        if (type != null) realmQuery.equalTo("type", type);
        return realmQuery.findAll();
    }

    public static float getExpensesCategoryPercentage(Date fromDate, Date toDate, Category category) {
        float totalCategory = getCategoryTotalByDate(fromDate, toDate, category);
        float total = getExpensesList(fromDate, DateUtilities.addDaysToDate(toDate, 1), IExpensesType.MODE_EXPENSES, null).sum("total").floatValue();
        return totalCategory * 100 / total;
    }

    public static List<Expense> cloneExpensesCollection(List<Expense> expenseList) {
        List<Expense> clonedExpenses = new ArrayList<>();
        for (Expense expense : expenseList) {
            Expense cloneExpense = new Expense();
            cloneExpense.setId(expense.getId());
            Category category = new Category();
            category.setName(expense.getCategory().getName());
            cloneExpense.setCategory(category);
            cloneExpense.setDate(expense.getDate());
            cloneExpense.setDescription(expense.getDescription());
            cloneExpense.setTotal(expense.getTotal());
            cloneExpense.setType(expense.getType());
            clonedExpenses.add(cloneExpense);
        }
        return clonedExpenses;
    }
}
