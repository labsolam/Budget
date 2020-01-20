package storage;

import org.flywaydb.core.Flyway;

import java.sql.*;

public class Storage
{
	private static final String DATABASE_LOCATION = "jdbc:h2:~/temp/.BudgetDb"; //TODO: Check this works on windows, make directory hidden

	public Storage()
	{

	}

	public void loadDatabase()
	{
		Flyway flyway = Flyway.configure().dataSource(DATABASE_LOCATION, "", "").load();
		flyway.migrate();
	}

	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(DATABASE_LOCATION);
	}
}
