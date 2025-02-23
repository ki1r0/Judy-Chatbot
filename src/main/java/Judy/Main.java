package Judy;
import java.io.IOException;

import Judy.ui.Judy;
import Judy.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Judy judy = new Judy("./data/judy.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Judy");
            fxmlLoader.<MainWindow>getController().setJudy(judy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
