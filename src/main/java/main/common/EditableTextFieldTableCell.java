package main.common;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

/**
 * Checks if a value can be accepted before the cell is committed.
 * TextFieldTableCell accepts any value and commits that value.
 * There's cases where duplicates aren't allowed in tables but are accepted anyway in a TextFieldTableCell.
 * EditableTextFieldTableCell overrides the editCommit of TextFieldTableCell keeping the modified
 * cell editable if certain conditions haven't been met. E.g. if it's a duplicate, keep the cell editable
 * until the user enters a unique value.
 *
 * @param <S> The type of the TableView generic type
 * @param <T> The type of the elements contained within the TableColumn.
 */
public abstract class EditableTextFieldTableCell<S, T> extends TextFieldTableCell<S, T>
{
	public EditableTextFieldTableCell(StringConverter<T> converter)
	{
		super(converter);
	}

	@Override
	public void commitEdit(T newValue)
	{
		if (!isEditing()) return;

		if (updateAllowed(newValue))
		{
			super.commitEdit(newValue);
		}
	}

	public abstract boolean updateAllowed(T newValue);

}
