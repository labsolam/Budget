package category.models;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Category
{
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleObjectProperty<BigDecimal> budget;
	private SimpleObjectProperty<BigDecimal> totalSpend;

	public Category(String name)
	{
		this(-1, name, new BigDecimal(0));
	}

	public Category(int id, String name, BigDecimal budget)
	{
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.totalSpend = new SimpleObjectProperty<>(new BigDecimal(0));
		this.budget = new SimpleObjectProperty<>(budget);
	}

	public Category(Category category)
	{
		this.id = new SimpleIntegerProperty(category.id.get());
		this.name = new SimpleStringProperty(category.name.get());
		this.budget = new SimpleObjectProperty<>(category.budget.get());
		this.totalSpend = new SimpleObjectProperty<>(category.totalSpend.get());
	}

	public int getId()
	{
		return this.id.get();
	}

	public void setId(int id)
	{
		this.id.set(id);
	}

	public SimpleIntegerProperty getIdProperty()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public SimpleStringProperty getNameProperty()
	{
		return this.name;
	}

	public BigDecimal getBudget()
	{
		return this.budget.getValue();
	}

	public void setBudget(BigDecimal budget)
	{
		this.budget.setValue(budget);
	}

	public SimpleObjectProperty<BigDecimal> getBudgetProperty()
	{
		return this.budget;
	}

	public BigDecimal getTotalSpend()
	{
		return this.totalSpend.getValue();
	}

	public void setTotalSpend(BigDecimal totalSpend)
	{
		this.totalSpend.setValue(totalSpend);
	}

	public SimpleObjectProperty<BigDecimal> getTotalSpendProperty()
	{
		return this.totalSpend;
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
