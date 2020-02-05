package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.Storage;

import java.sql.SQLException;

/**
 * Main.Main entry into the application.
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

        Model.initialiseModel();
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
