package category.common;

import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class CategoriesCommon
{
	private CategoriesCommon()
	{

	}

	public static StringConverter<BigDecimal> bigDecimalStringConverter = new BigDecimalStringConverter()
	{
		@Override
		public BigDecimal fromString(String value)
		{
			if (value.isBlank())
			{
				return new BigDecimal(0);
			}

			return super.fromString(value);
		}
	};
}
