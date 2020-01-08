package storage;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.flywaydb.core.internal.scanner.classpath.ClassPathLocationScanner;

import java.net.URLClassLoader;
import java.sql.*;

public class Storage
{
	private static final String DATABASE_LOCATION = "jdbc:h2:~/temp/.BudgetDb"; //TODO: Check this works on windows

	public Storage()
	{

	}

	public void loadDatabase() throws SQLException
	{
		Flyway flyway = Flyway.configure().dataSource(DATABASE_LOCATION,"","").load();
		flyway.migrate();


		ClassLoader.getSystemClassLoader().getResourceAsStream("db/migration/V1__Original_Structure.sql");
		ClassLoader.getSystemClassLoader().getResource("db/migration/V1__Original_Structure.sql");
		this.getClass().getClassLoader().getResourceAsStream("db/migration/V1__Original_Structure.sql");
		this.getClass().getResource("/db/migration/V1__Original_Structure.sql");
		this.getClass().getClassLoader().getResource("db/migration/V1__Original_Structure.sql");

		System.out.println();
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
