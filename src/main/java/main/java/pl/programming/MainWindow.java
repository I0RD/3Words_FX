package main.java.pl.programming;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final Text labelWords=new Text("3 WORDS");
    private final Text labelLat=new Text("Latitude");
    private final Text labelLong=new Text("longitude");
    private final ComboBox<Object> combo=new ComboBox<>();
    private final Button btnLonLat=new Button("Get Coordinates");
    private final Button btnWords=new Button("Get Words");
    private final Button btnRandom=new Button("Random Coordinates");
    private final TextField textFieldLat=new TextField(null);
    private final TextField textFieldLong=new TextField(null);
    private final TextField textFieldWords=new TextField(null);
    public final Hyperlink labelMap=new Hyperlink();
    private final GridPane root =new GridPane();
    private final ButtonsFunction buttonsFunction=new ButtonsFunction();

    @Override
    public void start(Stage stage)  {
        stage.setTitle("3 Words coordinate FX");
        stage.setWidth(700);
        stage.setHeight(500);
        stage.setMinHeight(300);
        stage.setMinWidth(400);
        GridPane.setMargin(labelWords,new Insets(30, 5, 5, 50));
        GridPane.setMargin(textFieldWords,new Insets(5, 5, 5, 50));
        textFieldWords.setPrefWidth(200);
        GridPane.setMargin(labelLat,new Insets(5, 5, 5, 50));
        GridPane.setMargin(textFieldLat,new Insets(5, 5, 5, 50));
        GridPane.setMargin(labelLong,new Insets(5, 5, 5, 50));
        GridPane.setMargin(textFieldLong,new Insets(5, 5, 5, 50));
        GridPane.setMargin(labelMap,new Insets(5,5,5,50));
        GridPane.setMargin(combo,new Insets(5, 5, 5, 100));
        GridPane.setMargin(btnWords,new Insets(5, 5, 5, 50));
        GridPane.setMargin(btnLonLat,new Insets(5, 5, 5, 50));
        GridPane.setMargin(btnRandom,new Insets(5, 5, 5, 50));
        btnWords.setPrefWidth(150);
        btnRandom.setPrefWidth(150);
        btnLonLat.setPrefWidth(150);
        root.add(labelWords,0,1);
        root.add(textFieldWords,0,2);
        root.add(labelLat,0,3);
        root.add(textFieldLat,0,4);
        root.add(labelLong,0,5);
        root.add(textFieldLong,0,6);
        root.add(labelMap,0,7);
        root.add(combo,0,8);
        root.add(btnWords,1,3);
        btnWords.setOnAction(actionEvent ->  {
            textFieldWords.setText(buttonsFunction.getWords(textFieldLong.getText(),textFieldLat.getText(), (Language) combo.getSelectionModel().getSelectedItem()));
            labelMap.setText("https://what3words.com//"+buttonsFunction.getWords(textFieldLat.getText(),textFieldLong.getText(), (Language) combo.getSelectionModel().getSelectedItem()));
        });
        root.add(btnLonLat,1,4);
        btnLonLat.setOnAction(actionEvent -> {
            String[] returned =buttonsFunction.getCoordinates(textFieldWords.getText());
            textFieldLong.setText(returned[0]);
            textFieldLat.setText(returned[1]);
            labelMap.setText(returned[2]);
        });
        root.add(btnRandom,1,5);
        btnRandom.setOnAction(actionEvent -> {
            textFieldLong.setText(buttonsFunction.randomLong());
            textFieldLat.setText(buttonsFunction.randomLat());
        });
        combo.getItems().addAll(
        new Language("German","de"),
        new Language("Norwegian","no"),
        new Language("Finnish","fi"),
        new Language("Russian","ru"),
        new Language("Portuguese","pt"),
        new Language("French","fr"),
        new Language("Bahasa Indonesia","id"),
        new Language("Mongolian","mn"),
        new Language("Swedish","sv"),
        new Language("Korean","ko"),
        new Language("Swahili","sw"),
        new Language("Afrikaans","af"),
        new Language("Greek","el"),
        new Language("English","en"),
        new Language("Italian","it"),
        new Language("Spanish","es"),
        new Language("Chinese","zh"),
        new Language("Czech","cs"),
        new Language("Xhosa","xh"),
        new Language("Arabic","ar"),
        new Language("Thai","th"),
        new Language("Japanese","ja"),
        new Language("Zulu","zu"),
        new Language("Polish","pl"),
        new Language("Danish","da"),
        new Language("Dutch","nl"),
        new Language("Turkish","tr")
        );
        labelMap.setOnAction(e -> getHostServices().showDocument(labelMap.getText()));
        combo.getSelectionModel().select(13);
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.show();
    }
    public static void main(String[] args) {launch();}
}