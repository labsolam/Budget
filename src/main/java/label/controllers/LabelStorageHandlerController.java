package label.controllers;

import label.models.Label;
import main.storage.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelStorageHandlerController
{
	private static final String GET_ALL_LABELS_SQL = "SELECT * FROM Label";
	private static final String NEW_LABEL_SQL = "INSERT INTO Label (NAME) VALUES ( ? ) ";
	private static final String UPDATE_LABEL_SQL = "UPDATE Label SET NAME = ? WHERE ID = ?";
	private static final String DELETE_LABEL_SQL = "DELETE FROM Label WHERE ID = ?";

	public LabelStorageHandlerController()
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

	public void create() throws SQLException
	{

	}

	public void update() throws SQLException
	{

	}

	public void delete() throws SQLException
	{

	}
}
