package main.common;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public class AppCommon
{
	public static final StringConverter<BigDecimal> bigDecimalStringConverter = new BigDecimalStringConverter()
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

	public static final UnaryOperator<TextFormatter.Change> onlyNumbersAllowedFilter = change ->
	{
		String newText = change.getControlNewText();

		if (newText.matches("([0-9][0-9]*)?"))
		{
			return change;
		}
		return null;
	};
}
