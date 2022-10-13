package main.java.pl.programming;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorWindow extends Application {

    private final String text;

    @Override
    public void start(Stage stage){
        stage.setTitle("Alert");
        Label statement=new Label(text);
        Scene scene=new Scene(statement);
        stage.setHeight(150);
        stage.setWidth(300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
    ErrorWindow(String text)
    {
        this.text=text;
    }
}