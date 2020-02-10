package account.controllers;

import account.models.AccountTypeEnum;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class NewAccountController
{
	@FXML TextField accountNameTextBox;
	@FXML Label accountExistsErrorLabel;
	@FXML Label failedToCreateAccountErrorLabel;
	@FXML Label accountNameInvalidLabel;
	@FXML VBox newAccountSidebar;
	@FXML ComboBox<AccountTypeEnum> accountTypeComboBox;

	public NewAccountController()
	{

	}

	public void initialize()
	{
		this.accountTypeComboBox.setItems(FXCollections.observableList(Arrays.asList(AccountTypeEnum.values())));
		this.accountTypeComboBox.getSelectionModel().select(AccountTypeEnum.DEBIT);
	}

	@FXML
	private void createAccount()
	{
		//TODO
	}

	@FXML
	private void closeCreateAccount()
	{
		this.accountNameTextBox.setText("");
		this.accountTypeComboBox.getSelectionModel().select(AccountTypeEnum.DEBIT);
		this.newAccountSidebar.getParent().setVisible(false);
	}

}
