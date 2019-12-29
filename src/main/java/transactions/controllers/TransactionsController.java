package transactions.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import transactions.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionsController
{
    private List<Transaction> transactions;

    @FXML
    private VBox addNewTransactionSidebar;

    public TransactionsController()
    {
        this.transactions = new ArrayList<>();
    }

    @FXML
    private void addTransaction()
    {
        //TODO: Add transaction to database
    }

    @FXML
    private void openNewTransactionSidebar()
    {
        this.addNewTransactionSidebar.setVisible(true);
    }
}

