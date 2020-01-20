package category.models;

public class Category
{
	private int id;
	private String name;

	public Category(String name)
	{
		this(-1, name);
	}

	public Category(int id, String name)
	{
		this.name = name;
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		Category category = (Category) o;

		return category.getName().equals(this.getName());

	}

	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}
}
