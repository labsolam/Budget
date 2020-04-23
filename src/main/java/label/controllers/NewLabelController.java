package label.controllers;

import javafx.fxml.FXML;
import label.models.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Model;

public class NewLabelController
{
	@FXML private TextField newLabelName;
	@FXML private javafx.scene.control.Label labelExistsErrorLabel;
	@FXML private javafx.scene.control.Label failedToCreateLabelErrorLabel;
	@FXML private javafx.scene.control.Label labelNameInvalidLabel;
	@FXML private VBox newLabelSidebar;

	private Model model;
	private LabelStorageHandler labelStorageHandler;

	public NewLabelController()
	{
		this.model = Model.getModel();
		this.labelStorageHandler = new LabelStorageHandler();
	}

	@FXML
	private void createNewLabel()
	{

	}

	private void addLabelToModel(Label label)
	{
		this.model.getLabels().add(label);
	}

	@FXML
	private void cancelCreateLabel()
	{
		this.newLabelName.setText(null); //TODO Check null is ok
		this.newLabelSidebar.getParent().setVisible(false);
	}

	private boolean doesLabelExist(String name)
	{
		return this.model.getLabels().stream().map(Label::getName).anyMatch(s -> s.equals(name));
	}
}
