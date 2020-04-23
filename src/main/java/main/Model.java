package main;

import account.controllers.AccountStorageHandler;
import account.models.Account;
import category.controllers.CategoryStorageHandler;
import category.models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import label.controllers.LabelStorageHandler;
import label.models.Label;

import java.sql.SQLException;

public class Model
{
	private static Model model;

	private ObservableList<Category> categories;
	private ObservableList<Account> accounts;
	private ObservableList<Label> labels;

	private Model()
	{

	}

	public static Model getModel()
	{
		if (model == null)
		{
			throw new IllegalStateException("Module should have been initialised before getModel is called.");
		}

		return model;
	}

	public static void initialiseModel() throws SQLException
	{
		if (model == null)
		{
			model = new Model();

			initialiseCategories();
			initialiseAccounts();
			initialiseLabels();
		}
	}

	public static void clearModel()
	{
		model = null;
	}

	private static void initialiseCategories() throws SQLException
	{
		model.categories = FXCollections.observableList(new CategoryStorageHandler().loadData());
	}

	private static void initialiseAccounts() throws SQLException
	{
		model.accounts = FXCollections.observableList(new AccountStorageHandler().loadData());
	}

	private static void initialiseLabels() throws SQLException
	{
		model.labels = FXCollections.observableList(new LabelStorageHandler().loadData());
	}

	public ObservableList<Category> getCategories()
	{
		return this.categories;
	}

	public ObservableList<Account> getAccounts()
	{
		return this.accounts;
	}

	public ObservableList<Label> getLabels()
	{
		return this.labels;
	}
}
