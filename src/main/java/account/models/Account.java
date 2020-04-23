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

	public Account(String name)
	{
		this(-1, name, AccountTypeEnum.DEBIT, new BigDecimal(0));
	}

	public Account(int id, String name, AccountTypeEnum type, BigDecimal startingBalance)
	{
		this.id = id;
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleObjectProperty<>(type);
		this.startingBalance = new SimpleObjectProperty<>(startingBalance);
	}

	public Account(Account account)
	{
		this.id = account.getId();
		this.name = new SimpleStringProperty(account.getName());
		this.type = new SimpleObjectProperty<>(account.getType());
		this.startingBalance = new SimpleObjectProperty<>(account.getStartingBalance());
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
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		final Account account = (Account) obj;

		return getName().equals(account.getName());
	}

	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}
}
