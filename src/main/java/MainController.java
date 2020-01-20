import category.controllers.CategoryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import overview.controllers.OverviewController;
import transactions.controllers.TransactionsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
	@FXML
	private TabPane navigation;


	public MainController()
	{
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//Initialise the tabs content
		try
		{
			OverviewController overviewController = new OverviewController();
			FXMLLoader overviewLoader = new FXMLLoader(getClass().getResource("/fxml/overview.fxml"));
			overviewLoader.setController(overviewController);
			GridPane overviewContent = overviewLoader.load();
			this.navigation.getTabs().get(Tabs.OVERVIEW.value).setContent(overviewContent);

			TransactionsController transactionsController = new TransactionsController();
			FXMLLoader transactionsLoader = new FXMLLoader(getClass().getResource("/fxml/transactions.fxml"));
			transactionsLoader.setController(transactionsController);
			StackPane transactionsContent = transactionsLoader.load();
			this.navigation.getTabs().get(Tabs.TRANSACTIONS.value).setContent(transactionsContent);

			CategoryController categoryController = new CategoryController();
			FXMLLoader categoriesLoader = new FXMLLoader(getClass().getResource("/fxml/categories.fxml"));
			categoriesLoader.setController(categoryController);
			StackPane categoriesContent = categoriesLoader.load();
			this.navigation.getTabs().get(Tabs.CATEGORIES.value).setContent(categoriesContent);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	private enum Tabs
	{
		OVERVIEW(0),
		TRANSACTIONS(1),
		CATEGORIES(2);

		private int value;

		Tabs(int value)
		{
			this.value = value;
		}
	}

}
