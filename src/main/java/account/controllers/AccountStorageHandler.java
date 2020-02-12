package account.controllers;

import account.models.Account;
import account.models.AccountTypeEnum;
import main.Model;
import storage.Storage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountStorageHandler
{
	private Model model;

	private static final String LOAD_ACCOUNT_DATA_SQL = "SELECT * FROM ACCOUNT";

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

//	public int create(String name, AccountTypeEnum type, BigDecimal startingBalance)
//	{
//		//TODO
//	}
}
