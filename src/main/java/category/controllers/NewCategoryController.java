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

	final private Model model;
	final private CategoryStorageHandler categoryStorageHandler;

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
		if (CategoryController.doesCategoryExist(this.categoryNameTextBox.getText()))
		{
			this.categoryExistsErrorLabel.setVisible(true);
		}
		else if (CategoryController.isNameValid(this.categoryNameTextBox.getText()))
		{
			try
			{
				int id = this.categoryStorageHandler.create(this.categoryNameTextBox.getText(),
						new BigDecimal(this.categoryBudgetTextBox.getText()));

				this.addCategoryToModel(new Category(id, this.categoryNameTextBox.getText(),
						new BigDecimal(this.categoryBudgetTextBox.getText()))); //TODO: Find a better way to do the conversion. Possibly with the TextFormatter? Seems to have a string converter.

				this.closeCreateCategory();
			} catch (SQLException e)
			{
				System.err.println(e.getMessage()); //TODO: Write an exception better than this
				this.failedToCreateCategoryErrorLabel.setVisible(true);
			}
		}
		else
		{
			this.categoryNameInvalidLabel.setVisible(true);
		}
	}

	private void addCategoryToModel(Category newCategory)
	{
		this.model.getCategories().add(newCategory);
	}

	@FXML
	private void closeCreateCategory()
	{
		this.newCategorySidebar.getParent().setVisible(false);
		this.failedToCreateCategoryErrorLabel.setVisible(false);
		this.categoryExistsErrorLabel.setVisible(false);
		this.categoryNameInvalidLabel.setVisible(false);
		this.categoryNameTextBox.setText(null);
		this.categoryBudgetTextBox.setText(null);
	}
}
