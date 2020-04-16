package category.controllers;

import category.models.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import main.Model;
import main.common.AppCommon;

import java.math.BigDecimal;
import java.sql.SQLException;

public class NewCategoryController
{
	@FXML private Label failedToCreateCategoryErrorLabel;
	@FXML private Label categoryExistsErrorLabel;
	@FXML private Label categoryNameInvalidLabel;
	@FXML private TextField categoryNameTextBox;
	@FXML private TextField categoryBudgetTextBox;
	@FXML private VBox newCategorySidebar;

	private Model model;
	private CategoryStorageHandler categoryStorageHandler;

	public NewCategoryController()
	{
		this.model = Model.getModel();
		this.categoryStorageHandler = new CategoryStorageHandler();
	}

	public void initialize()
	{
		this.categoryBudgetTextBox.setTextFormatter(new TextFormatter<>(AppCommon.bigDecimalStringConverter,
				new BigDecimal(0), AppCommon.onlyNumbersAllowedFilter));
	}

	@FXML
	private void createCategory()
	{
		if (this.doesCategoryExist(this.categoryNameTextBox.getText()))
		{
			this.categoryExistsErrorLabel.setVisible(true);
		}
		else
		{
			if(isNameValid(this.categoryNameTextBox.getText()))
			{
				try
				{
					int newCategoryId = this.categoryStorageHandler.create(this.categoryNameTextBox.getText(),
							new BigDecimal(this.categoryBudgetTextBox.getText()));

					this.addCategoryToModel(new Category(newCategoryId, this.categoryNameTextBox.getText(),
							new BigDecimal(this.categoryBudgetTextBox.getText()))); //TODO: Find a better way to do the conversion. Possibly with the TextFormatter? Seems to have a string converter.

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

	private void addCategoryToModel(Category newCategory)
	{
		this.model.getCategories().add(newCategory);
	}

	@FXML
	private void closeCreateCategory()
	{
		this.failedToCreateCategoryErrorLabel.setVisible(false);
		this.categoryExistsErrorLabel.setVisible(false);
		this.categoryNameInvalidLabel.setVisible(false);
		this.newCategorySidebar.getParent().setVisible(false);
		this.categoryNameTextBox.setText("");
		this.categoryBudgetTextBox.setText("");
	}

	/**
	 * Validates the category name.
	 *
	 * @param name Name of the category.
	 * @return True if the name is valid. False otherwise.
	 */
	private boolean isNameValid(String name)
	{
		if (name == null)
		{
			return false;
		}

		return !name.isBlank();
	}

	private boolean doesCategoryExist(String name)
	{
		return this.model.getCategories().stream().anyMatch(a -> a.getName().equals(name));
	}

}
