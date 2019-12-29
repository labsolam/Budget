package category.controllers;

import category.models.Category;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CategoryController
{
	private List<Category> categories;

	@FXML
	private VBox addNewCategorySidebar;

	public CategoryController()
	{
		this.categories = new ArrayList<>();
	}

	@FXML
	private void openNewCategorySidebar()
	{
		this.addNewCategorySidebar.setVisible(true);
	}
}
