package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Popup;
import javafx.stage.Stage;
import storage.Storage;

import java.sql.SQLException;

/**
 * Main entry into the application.
 */
public class Main extends Application
{
    private Model model;
    private Storage storage;

    @Override
    public void init() throws Exception
    {
        super.init();
        this.storage = new Storage();

        try
        {
            storage.startServer();
        }
        catch (SQLException e)
        {
            System.err.println("Failed to initialise server. Main init()");
            System.err.println(e.getMessage());
            Platform.exit();
        }

        storage.createAndMigrateDatabase();

        try
        {
            Model.initialiseModel();
            throw new SQLException();
        }
        catch (SQLException e)
        {
            //One of the initialisation methods failed. Try again
            Model.clearModel();

            try
            {
                Model.initialiseModel();
                throw new SQLException();
            }
            catch (SQLException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
            }
        }



        this.model = Model.getModel();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/main.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 275);

        primaryStage.setTitle("Budget");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setMaximized(true);
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        this.storage.stopServer();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
