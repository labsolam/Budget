package category.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CategoryController
{
	@FXML
	private VBox addNewCategorySidebar;

	public CategoryController()
	{

	}

	@FXML
	private void openNewCategorySidebar()
	{
		this.addNewCategorySidebar.setVisible(true);
	}

}
