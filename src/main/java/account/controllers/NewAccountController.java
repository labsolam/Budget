package account.controllers;

import account.models.Account;
import account.models.AccountTypeEnum;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import main.Model;
import main.common.AppCommon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;

public class NewAccountController
{
	@FXML TextField accountNameTextBox;
	@FXML Label accountExistsErrorLabel;
	@FXML Label failedToCreateAccountErrorLabel;
	@FXML Label accountNameInvalidLabel;
	@FXML VBox newAccountSidebar;
	@FXML ComboBox<AccountTypeEnum> accountTypeComboBox;
	@FXML TextField startingBalanceTextBox;

	final private Model model;
	final private AccountStorageHandler accountStorageHandler;

	public NewAccountController()
	{
		this.model = Model.getModel();
		this.accountStorageHandler = new AccountStorageHandler();
	}

	public void initialize()
	{
		this.accountTypeComboBox.setItems(FXCollections.observableList(Arrays.asList(AccountTypeEnum.values())));
		this.accountTypeComboBox.getSelectionModel().select(AccountTypeEnum.DEBIT);

		this.startingBalanceTextBox.setTextFormatter(new TextFormatter<>(AppCommon.bigDecimalStringConverter,
				new BigDecimal(0), AppCommon.onlyNumbersAllowedFilter));
	}

	@FXML
	private void createAccount()
	{
		if (AccountController.doesAccountExist(this.accountNameTextBox.getText()))
		{
			this.accountExistsErrorLabel.setVisible(true);
		}
		else
		{
			if (AccountController.isNameValid(this.accountNameTextBox.getText()))
			{
				try
				{
					int id = this.accountStorageHandler.create(this.accountNameTextBox.getText(),
							this.accountTypeComboBox.getValue(), new BigDecimal(this.startingBalanceTextBox.getText()));

					this.addAccountToModel(new Account(id, this.accountNameTextBox.getText(),
							this.accountTypeComboBox.getValue(), new BigDecimal(this.startingBalanceTextBox.getText())));
					//TODO: Find a better way to do the conversion. Possibly with the TextFormatter? Seems to have a string converter.

					this.closeCreateAccount();
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage()); //TODO: Write an exception better than this
					this.failedToCreateAccountErrorLabel.setVisible(true);
				}
			}
			else
			{
				this.accountNameInvalidLabel.setVisible(true);
			}
		}
	}

	private void addAccountToModel(Account account)
	{
		this.model.getAccounts().add(account);
	}

	@FXML
	private void closeCreateAccount()
	{
		this.newAccountSidebar.getParent().setVisible(false);
		this.accountExistsErrorLabel.setVisible(false);
		this.failedToCreateAccountErrorLabel.setVisible(false);
		this.accountNameInvalidLabel.setVisible(false);
		this.startingBalanceTextBox.setText(null);
		this.accountNameTextBox.setText(null);
		this.accountTypeComboBox.getSelectionModel().select(AccountTypeEnum.DEBIT);
	}
}
