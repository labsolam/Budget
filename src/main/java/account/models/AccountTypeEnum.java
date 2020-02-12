package account.models;

public enum AccountTypeEnum
{
	DEBIT(0),
	CREDIT(1),
	SAVINGS(2);

	//Order in the SQL
	private final int order;

	AccountTypeEnum(int order)
	{
		this.order = order;
	}

	public static AccountTypeEnum getEnumForOrder(int order)
	{
		for (AccountTypeEnum account : values())
		{
			if (account.order == order)
			{
				return account;
			}
		}
		throw new IllegalArgumentException("Account order number doesn't exist");
	}

}
