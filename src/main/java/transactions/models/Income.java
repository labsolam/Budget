package transactions.models;

import java.math.BigDecimal;

public class Income extends Transaction
{
    public Income(BigDecimal amount)
    {
         this.setAmount(amount);
    }
}
