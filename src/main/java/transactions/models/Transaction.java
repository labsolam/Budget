package transactions.models;

import category.models.Category;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Transaction
{
    private long id;
    private String name;
    private BigDecimal amount;
    private Date dateOfTransaction;
    private Category category;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Date getDateOfTransaction()
    {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction)
    {
        this.dateOfTransaction = dateOfTransaction;
    }
}
