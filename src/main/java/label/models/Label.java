package label.models;

import javafx.beans.property.SimpleStringProperty;

public class Label
{
	private int id;
	private SimpleStringProperty name;

	public Label(int id, String name)
	{
		this.id = id;
		this.name = new SimpleStringProperty(name);
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
		return name.get();
	}

	public SimpleStringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Label label = (Label) obj;

		return getName().equals(label.getName());
	}

	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}
}
