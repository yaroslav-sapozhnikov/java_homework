package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Adress");

        initRootlLayout();

        showPersonOnView();
    }


    public void initRootlLayout () {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showPersonOnView() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/mainScene.fxml"));
            AnchorPane personOnView = (AnchorPane) loader.load();
            rootLayout.setCenter(personOnView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage () {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
