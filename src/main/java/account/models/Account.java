package account.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account
{
	private int id;
	private SimpleStringProperty name;
	private SimpleObjectProperty<AccountTypeEnum> type;

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
}
