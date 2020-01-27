package main;

import category.controllers.CategoryStorageHandler;
import category.models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
	private static Model model;

	private ObservableList<Category> categories;

	private Model()
	{

	}

	public static Model getModel()
	{
		if (model == null)
		{
			throw new IllegalStateException("Module should have been initialised before getModel is called.");
		}

		return model;
	}

	public static void initialiseModel()
	{
		if (model == null)
		{
			model = new Model();

			initialiseCategories();
		}
	}

	private static void initialiseCategories()
	{
		model.categories = FXCollections.observableList(CategoryStorageHandler.loadData());
	}

	public ObservableList<Category> getCategories()
	{
		return this.categories;
	}
}
