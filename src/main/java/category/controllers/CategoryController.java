package category.controllers;

import category.common.CategoriesCommon;
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

import java.math.BigDecimal;
import java.sql.SQLException;

public class CategoryController
{
	private Model model;

	@FXML private TableView<Category> categoriesTable;
	@FXML private TableColumn<Category, String> categoryColumn;
	@FXML private TableColumn<Category, BigDecimal> totalSpendColumn;
	@FXML private TableColumn<Category, BigDecimal> budgetColumn;
	@FXML private TableColumn<Category, Button> deleteColumn;
	@FXML private VBox addNewCategorySidebar;

	public CategoryController()
	{
		this.model = Model.getModel();
	}

	public void initialize()
	{
		this.categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.categoryColumn.setOnEditCommit(event ->
		{
			try
			{
				Category newCategory = new Category(event.getRowValue());
				newCategory.setName(event.getNewValue());

				CategoryStorageHandler.update(newCategory);

				//Category updated successfully as no exception was thrown
				this.updateCategory(newCategory);
			} catch (SQLException e)
			{
				System.err.println(e.getMessage());
			}

		});

		this.budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
		this.budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn(CategoriesCommon.bigDecimalStringConverter));

		this.deleteColumn.setCellValueFactory(param ->
		{
			Button rowButton = new Button("Delete");
			rowButton.setUserData(param.getValue());
			rowButton.setOnAction(e ->
			{
				try
				{
					Category deleteCategory = (Category) ((Button) e.getSource()).getUserData();
					CategoryStorageHandler.delete(deleteCategory);

					//Category updated successfully as no exception was thrown
					this.deleteCategory(deleteCategory);

				} catch (SQLException ex)
				{
					System.err.println(ex.getMessage());
				}
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

	private void updateCategory(Category updateCategory)
	{
		for (Category modelCategory : this.model.getCategories())
		{
			if (modelCategory.getId() == updateCategory.getId())
			{
				modelCategory.setName(updateCategory.getName());
				modelCategory.setBudget(updateCategory.getBudget());
			}
		}
	}

	private void deleteCategory(Category deleteCategory)
	{
		this.model.getCategories().remove(deleteCategory);
	}

}
