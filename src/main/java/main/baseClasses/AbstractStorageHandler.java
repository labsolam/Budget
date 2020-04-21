package main.baseClasses;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractStorageHandler<T extends AbstractModel<T>>
{
	public abstract List<T> loadData() throws SQLException;

	public void create(T obj) throws SQLException
	{

	}

	public void update(T obj) throws SQLException
	{

	}

	public void delete(T obj) throws SQLException
	{

	}
}
