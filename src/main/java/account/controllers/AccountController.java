package account.controllers;

import account.models.Account;
import account.models.AccountTypeEnum;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import main.Model;
import main.common.AppCommon;

import java.math.BigDecimal;
import java.sql.SQLException;

public class AccountController
{
	final private Model model;
	final private AccountStorageHandler accountStorageHandler;

	@FXML TableView<Account> accountTable;
	@FXML TableColumn<Account, String> accountColumn;
	@FXML TableColumn<Account, AccountTypeEnum> typeColumn;
	@FXML TableColumn<Account, Button> deleteColumn;
	@FXML TableColumn<Account, BigDecimal> startingBalanceColumn;
	@FXML VBox addNewAccountSidebar;

	public AccountController()
	{
		this.model = Model.getModel();
		this.accountStorageHandler = new AccountStorageHandler();
	}

	public void initialize()
	{
		this.accountColumn.setCellValueFactory(c ->  c.getValue().nameProperty());
		this.accountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.accountColumn.setOnEditCommit(e ->
		{
			Account accountToUpdate = new Account(e.getRowValue());
			accountToUpdate.setName(e.getNewValue());
			this.updateAccount(accountToUpdate);
		});

		this.typeColumn.setCellValueFactory(c -> c.getValue().typeProperty());
		this.typeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(AccountTypeEnum.values()));
		this.typeColumn.setOnEditCommit(e ->
		{
			Account accountToUpdate = new Account(e.getRowValue());
			accountToUpdate.setType(e.getNewValue());
			this.updateAccount(accountToUpdate);
		}); //TODO: Item isn't selected when double clicking the combo box. The first item is empty. It should be the currently selected item

		this.startingBalanceColumn.setCellValueFactory(c -> c.getValue().startingBalanceProperty());
		this.startingBalanceColumn.setCellFactory(TextFieldTableCell.forTableColumn(AppCommon.bigDecimalStringConverter));
		this.startingBalanceColumn.setOnEditCommit(e ->
		{
			Account accountToUpdate = new Account(e.getRowValue());
			accountToUpdate.setStartingBalance(e.getNewValue());
			this.updateAccount(accountToUpdate);
		}); //TODO: Restrict the text box to numbers only. Currently accepts alphanumeric.

		this.deleteColumn.setCellValueFactory(param ->
		{
			Button button = new Button("Delete");
			button.setUserData(param.getValue());
			button.setOnAction(e ->
			{
				Account accountToDelete = ((Account) ((Button) e.getSource()).getUserData());
				this.deleteAccount(accountToDelete);
			});

			return new ReadOnlyObjectWrapper<>(button);
		});

		this.accountTable.setItems(this.model.getAccounts());
	}

	@FXML
	private void openNewAccountSidebar()
	{
		this.addNewAccountSidebar.setVisible(true);
	}

	private void updateAccount(Account accountToUpdate)
	{
		if (doesAccountExist(accountToUpdate.getName()))
		{
			//TODO Account exists
		}
		else if (isNameValid(accountToUpdate.getName()))
		{
			try
			{
				this.accountStorageHandler.update(accountToUpdate);

				for (Account accounts : this.model.getAccounts())
				{
					if (accounts.getId() == accountToUpdate.getId())
					{
						accounts.setName(accountToUpdate.getName());
						accounts.setType(accountToUpdate.getType());
						accounts.setStartingBalance(accountToUpdate.getStartingBalance());
					}
				}
			}
			catch (SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
		else
		{
			//TODO Account name is not valid
		}
	}

	private void deleteAccount(Account accountToDelete)
	{
		try
		{
			this.accountStorageHandler.delete(accountToDelete);
			this.model.getAccounts().remove(accountToDelete);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	static boolean isNameValid(String name)
	{
		if (name == null)
		{
			return false;
		}

		return !name.isBlank();
	}

	static boolean doesAccountExist(String name)
	{
		return Model.getModel().getAccounts().stream().anyMatch(a -> a.getName().equals(name));
	}

}
