package category.controllers;

import main.Model;
import category.models.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class NewCategoryController
{
	@FXML
	private Label failedToCreateCategoryErrorLabel;

	@FXML
	private Label categoryExistsErrorLabel;

	@FXML
	private Label categoryNameInvalidLabel;

	@FXML
	private TextField categoryNameTextBox;

	@FXML
	private VBox newCategorySidebar;

	private Model model;

	public NewCategoryController()
	{
		this.model = Model.getModel();
	}

	@FXML
	private void createCategory()
	{
		if (this.model.getCategories().contains(new Category(this.categoryNameTextBox.getText())))
		{
			this.categoryExistsErrorLabel.setVisible(true);
		}
		else
		{
			if(isCategoryNameValid(this.categoryNameTextBox.getText()))
			{
				try
				{
					int newCategoryId = CategoryStorageHandler.createCategory(this.categoryNameTextBox.getText());
					this.model.getCategories().add(new Category(newCategoryId, this.categoryNameTextBox.getText()));
					this.closeCreateCategory();
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage()); //TODO: Write an exception better than this
					this.failedToCreateCategoryErrorLabel.setVisible(true);
				}
			}
			else
			{
				//Name is not valid
				this.categoryNameInvalidLabel.setVisible(true);
			}
		}
	}

	@FXML
	private void closeCreateCategory()
	{
		this.failedToCreateCategoryErrorLabel.setVisible(false);
		this.categoryExistsErrorLabel.setVisible(false);
		this.categoryNameInvalidLabel.setVisible(false);
		this.newCategorySidebar.getParent().setVisible(false);
	}

	/**
	 * Validates the category name.
	 *
	 * @param name Name of the category.
	 * @return True if the name is valid. False otherwise.
	 */
	private boolean isCategoryNameValid(String name)
	{
		return !name.isBlank();
	}

}
