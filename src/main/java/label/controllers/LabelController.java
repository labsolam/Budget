package label.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.DefaultStringConverter;
import label.models.Label;
import main.Model;
import main.common.EditableTextFieldTableCell;

import java.sql.SQLException;

public class LabelController
{
	final private Model model;
	final private LabelStorageHandler labelStorageHandler;

	@FXML private TableView<Label> labelsTable;
	@FXML private TableColumn<Label, String> labelColumn;
	@FXML private TableColumn<Label, Button> deleteColumn;
	@FXML private VBox addNewLabelSidebar;

	public LabelController()
	{
		this.model = Model.getModel();
		this.labelStorageHandler = new LabelStorageHandler();
	}

	public void initialize()
	{
		this.labelColumn.setCellValueFactory(c -> c.getValue().nameProperty());
		this.labelColumn.setCellFactory(col -> new EditableTextFieldTableCell<>(new DefaultStringConverter())
		{
			@Override
			public boolean updateAllowed(String newValue)
			{

				return canUpdateLabel(newValue);
			}
		});

		this.labelColumn.setOnEditCommit(c ->
		{
			Label updateLabel = new Label(c.getRowValue());
			updateLabel.setName(c.getNewValue());
			this.updateLabel(updateLabel);
		});

		this.deleteColumn.setCellValueFactory(c ->
		{
			Button button = new Button("Delete");
			button.setUserData(c.getValue());
			button.setOnAction(e ->
			{
				Label toDelete = (Label) ((Button) e.getSource()).getUserData();
				this.deleteLabel(toDelete);
			});

			return new ReadOnlyObjectWrapper<>(button);
		});
		this.labelsTable.setItems(this.model.getLabels());
	}

	@FXML
	private void openNewLabelSidebar()
	{
		this.addNewLabelSidebar.setVisible(true);
	}

	private void deleteLabel(Label label)
	{
		try
		{
			this.labelStorageHandler.delete(label);
			this.model.getLabels().remove(label);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	private boolean canUpdateLabel(String newLabel)
	{
		if (doesLabelExist(newLabel))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Label already exists with the new name.");
			alert.show();
			return false;
		}
		else if (!isNameValid(newLabel))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Label name is invalid.");
			alert.show();
			return false;
		}

		return true;
	}

	private void updateLabel(Label labelToUpdate)
	{
		if (isNameValid(labelToUpdate.getName()))
		{
			try
			{
				labelStorageHandler.update(labelToUpdate);

				for (Label label : Model.getModel().getLabels())
				{
					if (label.getId() == labelToUpdate.getId())
					{
						label.setName(labelToUpdate.getName());
					}
				}
			}
			catch (SQLException e)
			{
				System.err.println(e.getMessage()); //TODO: Write an exception better than this
			}
		}
	}

	static boolean isNameValid(String name)
	{
		if (name == null)
		{
			return false;
		}

		return !name.isBlank();
	}

	static boolean doesLabelExist(String name)
	{
		return Model.getModel().getLabels().stream().map(Label::getName).anyMatch(s -> s.equals(name));
	}
}
