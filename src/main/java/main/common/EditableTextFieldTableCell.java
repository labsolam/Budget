package main.common;

import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.function.BooleanSupplier;

public class EditableTextFieldTableCell<S, T> extends TextFieldTableCell<S, T>
{
	private BooleanSupplier acceptChange;

	public EditableTextFieldTableCell()
	{
		super();
		this.setAcceptChange(() -> true); //Default to true
	}

	public EditableTextFieldTableCell(StringConverter<T> converter)
	{
		super(converter);
	}

	public EditableTextFieldTableCell(StringConverter<T> converter, BooleanSupplier booleanSupplier)
	{
		super(converter);
		this.setAcceptChange(booleanSupplier);
	}

	public void setAcceptChange(BooleanSupplier acceptChange)
	{
		this.acceptChange = acceptChange;
	}

	public BooleanSupplier getAcceptChange()
	{
		return this.acceptChange;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void commitEdit(T newValue)
	{
		if (! isEditing()) return;

		final TableView<S> table = getTableView();

		if(canUpdate())
		{
			if (table != null)
			{
				// Inform the TableView of the edit being ready to be committed.
				TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(
						table,
						table.getEditingCell(),
						TableColumn.editCommitEvent(),
						newValue
				);

				Event.fireEvent(getTableColumn(), editEvent);
			}

			// inform parent classes of the commit, so that they can switch us
			// out of the editing state.
			// This MUST come before the updateItem call below, otherwise it will
			// call cancelEdit(), resulting in both commit and cancel events being
			// fired (as identified in RT-29650)
			super.commitEdit(newValue);

			// update the item within this cell, so that it represents the new value
			updateItem(newValue, false);

			if (table != null)
			{
				// reset the editing cell on the TableView
				table.edit(-1, null);

				// request focus back onto the table, only if the current focus
				// owner has the table as a parent (otherwise the user might have
				// clicked out of the table entirely and given focus to something else.
				// It would be rude of us to request it back again.
//			ControlUtils.requestFocusOnControlOnlyIfCurrentFocusOwnerIsChild(table);
			}
		}
		else
		{
			System.out.println("NOOOOOOOOOOOOOOOO");
		}
	}

	public boolean canUpdate()
	{
		//Default to true
		return true;
	}
}
