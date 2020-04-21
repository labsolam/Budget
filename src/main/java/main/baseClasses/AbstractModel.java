package main.baseClasses;

public abstract class AbstractModel<T extends AbstractModel<T>>
{
	private int id;

	public AbstractModel(int id)
	{
		this.id = id;
	}

	public AbstractModel(T obj)
	{
		this.id = obj.getId();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
