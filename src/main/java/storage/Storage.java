package storage;

import org.flywaydb.core.Flyway;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage
{
	private static final String DATABASE_LOCATION = "jdbc:h2:tcp://localhost/~/temp/.BudgetDb"; //TODO: Check this works on windows, make directory hidden
	//	private static final String DATABASE_LOCATION = "jdbc:h2:~/temp/.BudgetDb";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	private Server server;

	public Storage()
	{

	}

	public void startServer() throws SQLException
	{
		if (this.server == null)
		{
			this.server = Server.createTcpServer().start();
		}
	}

	public void stopServer()
	{
		this.server.stop();
	}

	/**
	 * Migrate the database to the latest version.
	 *
	 * Note, a H2 database is automatically created if one doesn't exist. Hence, "create"AndMigrate.
	 * Only true if using an embedded database. Other you have to make your own.
	 */
	public void createAndMigrateDatabase()
	{
		Flyway flyway = Flyway.configure().dataSource(DATABASE_LOCATION, USERNAME, PASSWORD).load();
		flyway.migrate();
	}

	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(DATABASE_LOCATION);
	}
}
