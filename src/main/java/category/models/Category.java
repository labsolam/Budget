package category.models;

import javafx.beans.property.*;
import main.baseClasses.AbstractModel;

import java.math.BigDecimal;

public class Category extends AbstractModel<Category>
{
	private SimpleStringProperty name;
	private SimpleObjectProperty<BigDecimal> budget;
	private SimpleObjectProperty<BigDecimal> totalSpend;

	public Category(String name)
	{
		this(-1, name, new BigDecimal(0));
	}

	public Category(int id, String name, BigDecimal budget)
	{
		super(id);
		this.name = new SimpleStringProperty(name);
		this.totalSpend = new SimpleObjectProperty<>(new BigDecimal(0));
		this.budget = new SimpleObjectProperty<>(budget);
	}

	public Category(Category category)
	{
		super(category);
		this.name = new SimpleStringProperty(category.name.get());
		this.budget = new SimpleObjectProperty<>(category.budget.get());
		this.totalSpend = new SimpleObjectProperty<>(category.totalSpend.get());
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
