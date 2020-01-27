package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.Storage;

/**
 * Main.Main entry into the application.
 */
public class Main extends Application
{
    private Model model;

    @Override
    public void init() throws Exception
    {
        super.init();
        Storage storage = new Storage();
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


    public static void main(String[] args)
    {
        launch(args);
    }
}