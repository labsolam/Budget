package label.controllers;

import label.models.Label;
import main.storage.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LabelStorageHandler
{
	private static final String GET_ALL_LABELS_SQL = "SELECT * FROM Label";
	private static final String NEW_LABEL_SQL = "INSERT INTO Label (NAME) VALUES ( ? ) ";
	private static final String UPDATE_LABEL_SQL = "UPDATE Label SET NAME = ? WHERE ID = ?";
	private static final String DELETE_LABEL_SQL = "DELETE FROM Label WHERE ID = ?";

	public LabelStorageHandler()
	{

	}

	public List<Label> loadData() throws SQLException
	{
		List<Label> labels = new ArrayList<>();

		try
		(
			PreparedStatement preparedStatement  = Storage.getConnection().prepareStatement(GET_ALL_LABELS_SQL);
			ResultSet result = preparedStatement.executeQuery()
		)
		{
			while(result.next())
			{
				labels.add(new Label(result.getInt(1), result.getString(2)));
			}
		}

		return labels;
	}

	public int create(String name) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(NEW_LABEL_SQL,
				Statement.RETURN_GENERATED_KEYS)
		)
		{
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();

			if(preparedStatement.getGeneratedKeys().next())
			{
				return preparedStatement.getGeneratedKeys().getInt(1);
			}
			else
			{
				throw new SQLException("Failed to create label!"); //TODO: Query failed if there aren't any keys
			}
		}
	}

	public void update(Label label) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(UPDATE_LABEL_SQL)
		)
		{
			preparedStatement.setString(1, label.getName());
			preparedStatement.setInt(2, label.getId());
			int rowsModified = preparedStatement.executeUpdate();

			if (rowsModified != 1)
			{
				throw new SQLException("Failed to update label!");
			}
		}
	}

	public void delete(Label label) throws SQLException
	{
		try
		(
			PreparedStatement preparedStatement = Storage.getConnection().prepareStatement(DELETE_LABEL_SQL)
		)
		{
			preparedStatement.setInt(1, label.getId());

			int rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted != 1)
			{
				throw new SQLException("Failed to delete label!");
			}
		}
	}
}
