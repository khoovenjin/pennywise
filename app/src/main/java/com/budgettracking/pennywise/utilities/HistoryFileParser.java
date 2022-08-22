package com.budgettracking.pennywise.utilities;

import com.budgettracking.pennywise.entities.Expense;
import com.budgettracking.pennywise.interfaces.FileGeneratorParser;

import java.util.List;

public class HistoryFileParser implements FileGeneratorParser {

    //Generates file to store transactions
    @Override
    public String generateFileContent() {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Pennywise ").append(Utilities.formatDateToString(DateManager.getInstance().getDateFrom(), Utilities.getCurrentDateFormat())).append(" - ").append(Utilities.formatDateToString(DateManager.getInstance().getDateTo(), Utilities.getCurrentDateFormat())).append(addNextLine());
        List<Expense> expenseList = ExpensesManager.getInstance().getExpensesList();
        contentBuilder.append(addNextLine());
        for (Expense expense : expenseList) { //For every expenses stored in the data store from Realm
            //Adds the date
            contentBuilder.append(Utilities.formatDateToString(expense.getDate(), Utilities.getCurrentDateFormat())).append(addTab());
            //Adds the category
            contentBuilder.append(expense.getCategory().getName()).append(addTab());
            //Adds the description
            contentBuilder.append(expense.getDescription()).append(addTab());
            //Adds the total
            contentBuilder.append(expense.getTotal()).append(addNextLine());
        }
        contentBuilder.append(addNextLine());
        //Returns the total by the date selected by the user
        float total = Expense.getCategoryTotalByDate(DateManager.getInstance().getDateFrom(), DateManager.getInstance().getDateTo(), null);
        contentBuilder.append("Total").append(addTab()).append(total);
        return contentBuilder.toString();
    }

    public static String addNextLine() {
        return "\n";
    }

    public static String addTab() {
        return "\t";
    }

}
