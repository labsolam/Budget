package account.controllers;

import account.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import main.Model;

import java.math.BigDecimal;

public class AccountController
{
	private Model model;

	@FXML TableView<Account> accountTable;
	@FXML TableColumn<Account, String> accountColumn;
	@FXML TableColumn<Account, String> typeColumn;
	@FXML TableColumn<Account, Button> deleteColumn;
	@FXML TableColumn<Account, BigDecimal> startingBalanceColumn;
	@FXML VBox addNewAccountSidebar;

	public AccountController()
	{
		this.model = Model.getModel();
	}

	public void initialize()
	{

	}

	@FXML
	private void openNewAccountSidebar()
	{
		this.addNewAccountSidebar.setVisible(true);
	}

}
