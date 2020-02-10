package account.controllers;

import account.models.Account;
import main.Model;

import java.util.ArrayList;
import java.util.List;

public class AccountStorageHandler
{
	private Model model;

	public AccountStorageHandler()
	{
		this.model = Model.getModel();
	}

	public List<Account> loadData()
	{
		//TODO
		System.out.println("LOAD ACCOUNT DATA");

		return new ArrayList<>();
	}
}
