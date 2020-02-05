package category.controllers;

import category.models.Category;
import storage.Storage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryStorageHandler
{
	private static final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM Category";
	private static final String NEW_CATEGORY_SQL = "INSERT INTO Category (ID, NAME, BUDGET) VALUES ( null, ?, ? ) ";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE Category SET NAME = ?, BUDGET = ? WHERE ID = ?";
	private static final String DELETE_CATEGORY_SQL = "DELETE FROM Category WHERE ID = ?";

	public CategoryStorageHandler()
	{

	}

	public List<Category> loadData()
	{
		List<Category> categories = new ArrayList<>();

		try (
				PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(GET_ALL_CATEGORIES_SQL);
				ResultSet result = preparedStatement.executeQuery();
		)
		{

			while (result.next())
			{
				categories.add(new Category(result.getInt(1), result.getString(2), result.getBigDecimal(3)));
			}
		}
		catch (SQLException e)
		{
			//TODO: Throw exception specific for categories loading. Maybe get the caller to try again.
			// After a number of attempts give up and exit?
			System.err.println("SQL Error in Categories load data");
			System.err.println(e.getMessage());
		}

		return categories;
	}

	public int create(String categoryName, BigDecimal budget) throws SQLException
	{
		try (PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(NEW_CATEGORY_SQL, Statement.RETURN_GENERATED_KEYS))
		{
			preparedStatement.setString(1, categoryName);
			preparedStatement.setBigDecimal(2, budget);
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
			throw e; //TODO: Connection failed - better exception throwing
		}
	}

	public void delete(Category category) throws SQLException
	{
		try (PreparedStatement statement = Storage.getConnection().prepareStatement(DELETE_CATEGORY_SQL))
		{
			statement.setInt(1, category.getId());
			int rowsDeleted = statement.executeUpdate();

			if(rowsDeleted == 0)
			{
				throw new SQLException("Failed to update!"); //TODO: Find a better exception and/or message
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in Categories deleteCategories method");
			System.err.println(e.getMessage());
			throw e; //TODO: Connection failed  - better exception throwing needed
		}
	}

	public void update(Category category) throws SQLException
	{
		try (PreparedStatement statement = Storage.getConnection().prepareStatement(UPDATE_CATEGORY_SQL);)
		{
			statement.setString(1, category.getName());
			statement.setBigDecimal(2, category.getBudget());
			statement.setInt(3, category.getId());

			int rowsModified = statement.executeUpdate();

			if(rowsModified == 0)
			{
				throw new SQLException("Failed to update!"); //TODO: Find a better exception and/or message
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in Categories updateCategories method");
			System.err.println(e.getMessage());
			throw e; //TODO: Connection failed  - better exception throwing needed
		}
	}
}
