package transactions.models;

import java.math.BigDecimal;

public class Expense extends Transaction
{
    public Expense(BigDecimal amount)
    {
        this.setAmount(amount);
    }
}
