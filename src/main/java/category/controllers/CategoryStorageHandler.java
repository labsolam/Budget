package category.controllers;

import category.models.Category;
import storage.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryStorageHandler
{
	private static final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM Category";
	private static final String NEW_CATEGORY_SQL = "INSERT INTO Category (ID, NAME) VALUES ( null, ? ) ";

	public static List<Category> loadData()
	{
		List<Category> categories = new ArrayList<>();

		try (
				PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(GET_ALL_CATEGORIES_SQL);
				ResultSet result = preparedStatement.executeQuery();
		)
		{

			while (result.next())
			{
				categories.add(new Category(result.getInt(1), result.getString(2)));
			}
		}
		catch (SQLException e)
		{
			//TODO: Throw exception specific for categories loading. Maybe get the caller to try again.
			// After a number of attempts give up and exit?
			System.err.println(e.getMessage());
		}

		return categories;
	}

	public static int createCategory(String categoryName) throws SQLException
	{
		try (PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(NEW_CATEGORY_SQL, Statement.RETURN_GENERATED_KEYS))
		{
			preparedStatement.setString(1, categoryName);
			preparedStatement.executeUpdate();

			if (preparedStatement.getGeneratedKeys().next())
			{
				return preparedStatement.getGeneratedKeys().getInt(1);
			}
			else
			{
				throw new SQLException(); //TODO: Query failed if there aren't any keys
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			throw e; //TODO: Connection failed
		}
	}

	public static void deleteCategory()
	{
		//TODO
	}

	public static void updateCategory()
	{
		//TODO
	}
}
