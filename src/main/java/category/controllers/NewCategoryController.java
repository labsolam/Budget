package category.controllers;

import category.common.CategoriesCommon;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import main.Model;
import category.models.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.function.UnaryOperator;

public class NewCategoryController
{
	@FXML private Label failedToCreateCategoryErrorLabel;
	@FXML private Label categoryExistsErrorLabel;
	@FXML private Label categoryNameInvalidLabel;
	@FXML private TextField categoryNameTextBox;
	@FXML private TextField categoryBudgetTextBox;
	@FXML private VBox newCategorySidebar;

	private Model model;

	public NewCategoryController()
	{
		this.model = Model.getModel();
	}

	public void initialize()
	{
		UnaryOperator<Change> filter = change ->
		{
			String newText = change.getControlNewText();

			if (newText.matches("([1-9][0-9]*)?"))
			{
				return change;
			}
			return null;
		};

		this.categoryBudgetTextBox.setTextFormatter(new TextFormatter<>(CategoriesCommon.bigDecimalStringConverter, new BigDecimal(0), filter));
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
					int newCategoryId = CategoryStorageHandler.create(this.categoryNameTextBox.getText(), new BigDecimal(this.categoryBudgetTextBox.getText()));

					this.addCategoryToModel(new Category(newCategoryId, this.categoryNameTextBox.getText(), new BigDecimal(this.categoryBudgetTextBox.getText()))); //TODO: Find a better way to do the conversion. Possibly with the TextFormatter? Seems to have a string converter.
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
	private boolean isCategoryNameValid(String name)
	{
		if (name == null)
		{
			return false;
		}

		return !name.isBlank();
	}

}
