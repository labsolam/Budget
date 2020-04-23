package account.controllers;

import account.models.Account;
import account.models.AccountTypeEnum;
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
	private static final String LOAD_ACCOUNT_DATA_SQL = "SELECT * FROM ACCOUNT";
	private static final String CREATE_ACCOUNT_SQL = "INSERT INTO ACCOUNT(Name, Type, StartingBalance) VALUES (?, ?, ?)";
	private static final String UPDATE_ACCOUNT_SQL = "UPDATE ACCOUNT SET Name = ?, Type = ?, StartingBalance = ? WHERE ID = ?";
	private static final String DELETE_ACCOUNT_SQL = "DELETE FROM ACCOUNT WHERE ID = ?";

	public AccountStorageHandler()
	{
	}

	public List<Account> loadData() throws SQLException
	{
		List<Account> accounts = new ArrayList<>();

		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(LOAD_ACCOUNT_DATA_SQL);
			ResultSet results = preparedStatement.executeQuery()
		)
		{
			while (results.next())
			{
				accounts.add(new Account(results.getInt(1), results.getString(2),
						AccountTypeEnum.valueOf(results.getString(3)), results.getBigDecimal(4)));
			}
		}

		return accounts;
	}

	public int create(String name, AccountTypeEnum type, BigDecimal startingBalance) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(CREATE_ACCOUNT_SQL,
				Statement.RETURN_GENERATED_KEYS)
		)
		{
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, type.toString());
			preparedStatement.setBigDecimal(3, startingBalance);
			preparedStatement.executeUpdate();

			if (preparedStatement.getGeneratedKeys().next())
			{
				return preparedStatement.getGeneratedKeys().getInt(1);
			} else
			{
				throw new SQLException("Failed to create account!");
			}
		}
	}

	public void update(Account account) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(UPDATE_ACCOUNT_SQL)
		)
		{
			preparedStatement.setString(1, account.getName());
			preparedStatement.setString(2, account.getType().toString());
			preparedStatement.setBigDecimal(3, account.getStartingBalance());
			preparedStatement.setInt(4, account.getId());

			int rowsModified = preparedStatement.executeUpdate();

			if (rowsModified != 1)
			{
				throw new SQLException("Failed to update account!");
			}
		}
	}

	public void delete(Account account) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(DELETE_ACCOUNT_SQL)
		)
		{
			preparedStatement.setInt(1, account.getId());

			int rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted != 1)
			{
				throw new SQLException("Failed to delete account!");
			}
		}
	}
}
