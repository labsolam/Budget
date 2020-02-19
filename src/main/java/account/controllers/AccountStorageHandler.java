package account.controllers;

import account.models.Account;
import account.models.AccountTypeEnum;
import main.Model;
import main.storage.Storage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountStorageHandler
{
	private Model model;

	private static final String LOAD_ACCOUNT_DATA_SQL = "SELECT * FROM ACCOUNT";
	private static final String CREATE_ACCOUNT_SQL = "INSERT INTO ACCOUNT(Name, Type, StartingBalance) VALUES (?, ?, ?)";

	public AccountStorageHandler()
	{
		this.model = Model.getModel();
	}

	public List<Account> loadData() throws SQLException
	{
		List<Account> accounts = new ArrayList<>();

		try
		(
				PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(LOAD_ACCOUNT_DATA_SQL);
				ResultSet results = preparedStatement.executeQuery();
		)
		{
			while (results.next())
			{
				accounts.add(new Account(results.getInt(1), results.getString(2),
						AccountTypeEnum.getEnumForOrder(results.getInt(3)), results.getBigDecimal(4)));
			}
		}

		return accounts;
	}

	public int create(String name, AccountTypeEnum type, BigDecimal startingBalance) throws SQLException
	{
		PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(CREATE_ACCOUNT_SQL,
				Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setString(1, name);
		preparedStatement.setObject(2, type);
		preparedStatement.setBigDecimal(3, startingBalance);
		preparedStatement.executeUpdate();

		if (preparedStatement.getGeneratedKeys().next())
		{
			return preparedStatement.getGeneratedKeys().getInt(1);
		}
		else
		{
			throw new SQLException(); //TODO: Query failed if there aren't any keys
		}
	}
}
