package storage;

import category.controllers.CategoryTable;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class Storage
{
	private static List<Table> TableClasses = new ArrayList<>();

	private static final String DATABASE_LOCATION = "";

	public Storage()
	{
		this.register(new CategoryTable());
	}

	public void register(Table Table)
	{
		if (!TableClasses.contains(Table))
		{
			TableClasses.add(Table);
		}
	}

	public void createDataTables()
	{
		for (Table Table : TableClasses)
		{
			if(!Table.isTableCreated())
			{
				Table.createDatabase();
			}
		}
	}

//	public void getConnection()
//	{
//		try (
//				Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("select * from bob");
//		)
//		{
//
//		} catch (SQLException e)
//		{
//			System.out.println(e.getLocalizedMessage());
//			System.out.println(e.getMessage());
//			System.out.println("Caught SQL Excpetion");
//		}
//	}
}
