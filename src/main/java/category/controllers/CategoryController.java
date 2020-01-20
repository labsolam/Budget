package category.controllers;

import category.models.Category;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CategoryController
{
	private final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM Category";
	private final String NEW_CATEGORY_SQL = "INSERT INTO Category (ID, NAME) VALUES ( null, ? ) ";

	@FXML
	private VBox addNewCategorySidebar;

	private List<Category> categories;

	public CategoryController()
	{
		this.categories = new ArrayList<>();
	}

	@FXML
	private void openNewCategorySidebar()
	{
		this.addNewCategorySidebar.setVisible(true);
	}

	@FXML
	private void createCategory()
	{
		/*
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
		} */
	}

}
