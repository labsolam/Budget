package label.controllers;

import javafx.fxml.FXML;
import label.models.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Model;

import java.sql.SQLException;

public class NewLabelController
{
	@FXML private TextField newLabelName;
	@FXML private javafx.scene.control.Label labelExistsErrorLabel;
	@FXML private javafx.scene.control.Label failedToCreateLabelErrorLabel;
	@FXML private javafx.scene.control.Label labelNameInvalidLabel;
	@FXML private VBox newLabelSidebar;

	final private Model model;
	final private LabelStorageHandler labelStorageHandler;

	public NewLabelController()
	{
		this.model = Model.getModel();
		this.labelStorageHandler = new LabelStorageHandler();
	}

	@FXML
	private void createLabel()
	{
		String labelName = this.newLabelName.getText();

		if (LabelController.doesLabelExist(labelName))
		{
			this.labelExistsErrorLabel.setVisible(true);
		}
		else
		{
			if (LabelController.isNameValid(labelName))
			{
				try
				{
					int rowNumber = this.labelStorageHandler.create(labelName);

					this.addLabelToModel(new Label(rowNumber, labelName));

					this.closeCreateLabel();
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage()); //TODO: Write an exception better than this
					this.failedToCreateLabelErrorLabel.setVisible(true);
				}
			}
			else
			{
				this.labelNameInvalidLabel.setVisible(true);
			}
		}
	}

	private void addLabelToModel(Label label)
	{
		this.model.getLabels().add(label);
	}

	@FXML
	private void closeCreateLabel()
	{
		this.newLabelSidebar.getParent().setVisible(false);
		this.labelNameInvalidLabel.setVisible(false);
		this.labelExistsErrorLabel.setVisible(false);
		this.failedToCreateLabelErrorLabel.setVisible(false);
		this.newLabelName.setText(null);
	}
}
