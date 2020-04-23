package category.models;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Category
{
	private int id;
	final private SimpleStringProperty name;
	final private SimpleObjectProperty<BigDecimal> budget;
	final private SimpleObjectProperty<BigDecimal> totalSpend;

	public Category(String name)
	{
		this(-1, name, new BigDecimal(0));
	}

	public Category(int theId, String name, BigDecimal budget)
	{
		this.id = theId;
		this.name = new SimpleStringProperty(name);
		this.totalSpend = new SimpleObjectProperty<>(new BigDecimal(0));
		this.budget = new SimpleObjectProperty<>(budget);
	}

	public Category(Category category)
	{
		this.id = category.getId();
		this.name = new SimpleStringProperty(category.name.get());
		this.budget = new SimpleObjectProperty<>(category.budget.get());
		this.totalSpend = new SimpleObjectProperty<>(category.totalSpend.get());
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int newId)
	{
		this.id = newId;
	}

	public String getName()
	{
		return this.name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public SimpleStringProperty nameProperty()
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

	public SimpleObjectProperty<BigDecimal> budgetProperty()
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

	public SimpleObjectProperty<BigDecimal> totalSpendProperty()
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
