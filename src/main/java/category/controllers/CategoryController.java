package category.controllers;

import category.models.Category;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import main.Model;
import main.common.AppCommon;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CategoryController
{
	private Model model;
	private CategoryStorageHandler categoryStorageHandler;

	@FXML private TableView<Category> categoriesTable;
	@FXML private TableColumn<Category, String> categoryColumn;
	@FXML private TableColumn<Category, BigDecimal> totalSpendColumn;
	@FXML private TableColumn<Category, BigDecimal> budgetColumn;
	@FXML private TableColumn<Category, Button> deleteColumn;
	@FXML private VBox addNewCategorySidebar;

	public CategoryController()
	{
		this.model = Model.getModel();
		this.categoryStorageHandler = new CategoryStorageHandler();
	}

	public void initialize()
	{
		this.categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.categoryColumn.setOnEditCommit(event ->
		{
			Category categoryToUpdate = new Category(event.getRowValue());
			categoryToUpdate.setName(event.getNewValue());
			this.updateCategory(categoryToUpdate);
		});

		this.budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
		this.budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn(AppCommon.bigDecimalStringConverter));
		this.budgetColumn.setOnEditCommit(event ->
		{
			Category categoryToUpdate = new Category(event.getRowValue());
			categoryToUpdate.setBudget(event.getNewValue());
			this.updateCategory(categoryToUpdate);
		});

		this.deleteColumn.setCellValueFactory(param ->
		{
			Button rowButton = new Button("Delete");
			rowButton.setUserData(param.getValue());
			rowButton.setOnAction(e ->
			{
				Category toDelete = (Category) ((Button) e.getSource()).getUserData();
				this.deleteCategory(toDelete);
			});

			return new ReadOnlyObjectWrapper<>(rowButton);
		});

		this.categoriesTable.setItems(this.model.getCategories());
	}

	@FXML
	private void openNewCategorySidebar()
	{
		this.addNewCategorySidebar.setVisible(true);
	}

	private void updateCategory(Category categoryToUpdate)
	{
		try
		{
			this.categoryStorageHandler.update(categoryToUpdate);

			for (Category modelCategory : this.model.getCategories())
			{
				if (modelCategory.getId() == categoryToUpdate.getId())
				{
					modelCategory.setName(categoryToUpdate.getName());
					modelCategory.setBudget(categoryToUpdate.getBudget());
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage()); //TODO: Better error message
		}
	}

	private void deleteCategory(Category categoryToDelete)
	{
		try
		{
			this.categoryStorageHandler.delete(categoryToDelete);
			this.model.getCategories().remove(categoryToDelete);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

}
