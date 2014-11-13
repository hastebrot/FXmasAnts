package de.jug_h;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXmasAnts extends Application {

    //---------------------------------------------------------------------------------------------
    // MAIN METHOD.
    //---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Application.launch(FXmasAnts.class, args);
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public void start(Stage primaryStage) {
        PlayField playField = new PlayField();

        Pane rootPane = new AnchorPane();
        playField.attachTo(rootPane);

        Scene scene = new Scene(rootPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.show();
    }

}
