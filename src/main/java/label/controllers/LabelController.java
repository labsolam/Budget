package label.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import label.models.Label;

public class LabelController
{
	@FXML private TableView<Label> labelsTable;
	@FXML private TableColumn<Label, String> labelColumn;
	@FXML private TableColumn<Label, Button> deleteColumn;
	@FXML private VBox addNewLabelSidebar;

	public LabelController()
	{

	}

	@FXML
	private void openNewLabelSidebar()
	{
		this.addNewLabelSidebar.setVisible(true);
	}

}
