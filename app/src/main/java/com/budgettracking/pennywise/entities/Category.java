package com.budgettracking.pennywise.entities;

import com.budgettracking.pennywise.interfaces.IExpensesType;
import com.budgettracking.pennywise.utilities.RealmManager;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
//TO SET CATEGORIES IN THE APP.

public class Category extends RealmObject { //Used Realm as in-app data store

    @PrimaryKey
    private String id; //The primary key of Category

    private String name; //Name of Category
    private int type; //Type of category
    private RealmList<Expense> expenses; //Store under the array list

    public Category() {
    }

    //Main Constructor
    public Category(String name, @IExpensesType int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RealmList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(RealmList<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Category> getCategoriesIncome() {
        return getCategoriesForType(IExpensesType.MODE_INCOME);
    }

    public static List<Category> getCategoriesExpense() {
        return getCategoriesForType(IExpensesType.MODE_EXPENSES);
    }

    public static List<Category> getCategoriesForType(@IExpensesType int type){
        return RealmManager.getInstance().getRealmInstance().where(Category.class)
                .equalTo("type", type)
                .findAll();
    }

}
