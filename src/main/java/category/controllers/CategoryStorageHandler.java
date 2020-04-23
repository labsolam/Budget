package category.controllers;

import category.models.Category;
import main.storage.Storage;

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
	private static final String NEW_CATEGORY_SQL = "INSERT INTO Category (NAME, BUDGET) VALUES ( ?, ? ) ";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE Category SET NAME = ?, BUDGET = ? WHERE ID = ?";
	private static final String DELETE_CATEGORY_SQL = "DELETE FROM Category WHERE ID = ?";

	public CategoryStorageHandler()
	{

	}

	public List<Category> loadData() throws SQLException
	{
		List<Category> categories = new ArrayList<>();

		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(GET_ALL_CATEGORIES_SQL);
			ResultSet result = preparedStatement.executeQuery()
		)
		{
			while (result.next())
			{
				categories.add(new Category(result.getInt(1), result.getString(2), result.getBigDecimal(3)));
			}
		}

		return categories;
	}

	public int create(String categoryName, BigDecimal budget) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(NEW_CATEGORY_SQL,
				Statement.RETURN_GENERATED_KEYS)
		)
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
				throw new SQLException("Failed to create category!");
			}
		}
	}

	public void update(Category category) throws SQLException
	{
		try
		(
			PreparedStatement statement = Storage.getConnection().prepareStatement(UPDATE_CATEGORY_SQL)
		)
		{
			statement.setString(1, category.getName());
			statement.setBigDecimal(2, category.getBudget());
			statement.setInt(3, category.getId());

			int rowsModified = statement.executeUpdate();

			if (rowsModified == 0)
			{
				throw new SQLException("Failed to update category!");
			}
		}
	}

	public void delete(Category category) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(DELETE_CATEGORY_SQL)
		)
		{
			preparedStatement.setInt(1, category.getId());

			int rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted != 1)
			{
				throw new SQLException("Failed to delete category!");
			}
		}
	}
}
