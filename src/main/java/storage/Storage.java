package storage;

import org.flywaydb.core.Flyway;

public class Storage
{
	private static final String DATABASE_LOCATION = "jdbc:h2:~/temp/.BudgetDb"; //TODO: Check this works on windows

	public Storage()
	{

	}

	public void loadDatabase()
	{
		Flyway flyway = Flyway.configure().dataSource(DATABASE_LOCATION,"","").load();
		flyway.migrate();
	}
}
