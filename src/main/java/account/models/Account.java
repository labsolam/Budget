package account.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class Account
{
	private int id;
	private SimpleStringProperty name;
	private SimpleObjectProperty<AccountTypeEnum> type;
	private SimpleObjectProperty<BigDecimal> startingBalance;

	public Account(int id, String name, AccountTypeEnum type, BigDecimal startingBalance)
	{
		this.id = id;
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleObjectProperty<>(type);
		this.startingBalance = new SimpleObjectProperty<>(startingBalance);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name.get();
	}

	public SimpleStringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public AccountTypeEnum getType()
	{
		return type.get();
	}

	public SimpleObjectProperty<AccountTypeEnum> typeProperty()
	{
		return type;
	}

	public void setType(AccountTypeEnum type)
	{
		this.type.set(type);
	}

	public BigDecimal getStartingBalance()
	{
		return startingBalance.get();
	}

	public SimpleObjectProperty<BigDecimal> startingBalanceProperty()
	{
		return startingBalance;
	}

	public void setStartingBalance(BigDecimal startingBalance)
	{
		this.startingBalance.set(startingBalance);
	}

	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}

		if (!(obj instanceof Account))
		{
			return false;
		}

		final Account comparedObj = (Account) obj;

		return comparedObj.getName().equals(this.getName());
	}
}
