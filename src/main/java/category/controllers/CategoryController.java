package category.controllers;

import category.models.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable
{

	/* Categories.fxml */

	@FXML
	private Button categoriesOpenAddCategorySidebar;

	@FXML
	private VBox categoriesAddNewCategorySidebar;

	/* New Category */

	@FXML
	private TextField newCategoryCategoryNameTextBox;

	@FXML
	private Label newCategoryCategoryExistsErrorLabel;

	@FXML
	private Label newCategoryFailedToCreateCategoryErrorLabel;

	@FXML
	private Button newCategoryCreateCategoryButton;

	/* SQL */

	private final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM Category";
	private final String NEW_CATEGORY_SQL = "INSERT INTO Category (ID, NAME) VALUES ( null, ? ) ";

	/* Instance variables */

	private List<Category> categories;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.categoriesOpenAddCategorySidebar.setOnAction(e -> openNewCategorySidebar());
	}

	public CategoryController()
	{
		this.categories = new ArrayList<>();
	}

	@FXML
	private void openNewCategorySidebar()
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/newCategory.fxml"));
		loader.setController(this);

		try
		{
			this.categoriesAddNewCategorySidebar.getChildren().add(loader.load());
			this.newCategoryCreateCategoryButton.setOnAction(e -> createCategory());
			this.categoriesAddNewCategorySidebar.setVisible(true);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	@FXML
	private void createCategory()
	{
		if (this.categories.contains(new Category(this.newCategoryCategoryNameTextBox.getText())))
		{
			this.newCategoryCategoryExistsErrorLabel.setVisible(true);
		}
		else
		{
			try (PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(NEW_CATEGORY_SQL, Statement.RETURN_GENERATED_KEYS))
			{
				preparedStatement.setString(1, this.newCategoryCategoryNameTextBox.getText());
				preparedStatement.executeUpdate();

				if (preparedStatement.getGeneratedKeys().next())
				{
					this.categories.add(new Category(preparedStatement.getGeneratedKeys().getInt(1), this.newCategoryCategoryNameTextBox.getText()));
					this.categoriesAddNewCategorySidebar.setVisible(false);
				}
				else
				{
					this.newCategoryFailedToCreateCategoryErrorLabel.setVisible(true);
				}
			}
			catch (SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}

	private void getAllCategories()
	{

	}


}
