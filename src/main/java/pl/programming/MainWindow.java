package pl.programming;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindow extends Application {
    private final Text labelWords=new Text("3 WORDS");
    private final Text labelLat=new Text("Latitude");
    private final Text labelLong=new Text("longitude");
    private final TextField textFieldWords=new TextField(null);
    private final TextField textFieldLat=new TextField(null);
    private final TextField textFieldLong=new TextField(null);
    private final ComboBox combo=new ComboBox<>();
    private final Button btnLonLat=new Button("Get Coordinates");
    private final Button btnWords=new Button("Get Words");
    private final Button btnRandom=new Button("Random Coordinates");
    private final Hyperlink labelMap=new Hyperlink();
    private final GridPane root =new GridPane();

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
        btnWords.setOnAction(actionEvent ->  getWords());
        root.add(btnLonLat,1,4);
        btnLonLat.setOnAction(actionEvent -> getCoordinates());
        root.add(btnRandom,1,5);
        btnRandom.setOnAction(actionEvent -> randomLatLong());
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
    private void randomLatLong()
    {
        textFieldLong.setText(String.format(Locale.US,"%.2f",23-new Random().nextInt(11)+new Random().nextDouble(0.9)));
        textFieldLat.setText(String.format(Locale.US,"%.2f",55-new Random().nextInt(5)+new Random().nextDouble(0.9)));
    }
    private void getWords()
    {
        String link=null;
        Language actual= (Language) combo.getSelectionModel().getSelectedItem();
        if(isInteger(textFieldLat.getText(),0)&&isInteger(textFieldLong.getText(),0))
        {
            link="https://api.what3words.com/v3/convert-to-3wa?coordinates="+textFieldLat.getText()+"%2C"+textFieldLong.getText()+"&clip-to-country=PL&language="+actual.getCode()+"&key=I1G2OFF8";
        }
        else{
            ErrorWindow errorWindow=new ErrorWindow("You must enter the coordinates in the label");
            try {
                errorWindow.start(new Stage());
            } catch (Exception ignored) {
            }
        }
            if(link!=null)
            {
                try {
                    String returned;
                    Connection connection=new Connection(link);
                    returned=connection.returnConnection();
                    if(!returned.isEmpty())
                    {
                        String[] results=returned.split("\"");
                        textFieldWords.setText(results[31]);
                        labelMap.setText(results[39]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    private void getCoordinates()
    {
        String words=textFieldWords.getText();
        String regex = "[n+\\p{IsAlphabetic}\\p{S}.\\p{IsAlphabetic}\\p{S}\\p{IsAlphabetic}\\p{S}]";
        Pattern patter=Pattern.compile(regex);
        Matcher matcher=patter.matcher(words);
        words= URLEncoder.encode(words, StandardCharsets.UTF_8);
        if(matcher.find())
        {
                String link="https://api.what3words.com/v3/convert-to-coordinates?words="+words+"&key=I1G2OFF8";
            try {
                Connection connection= new Connection(link);
                String returned = connection.returnConnection();
                if (!returned.isEmpty()) {
                    String[] results = returned.split("\"");
                    textFieldLong.setText(results[16].substring(1,results[16].indexOf('.')+3));
                    textFieldLat.setText(results[18].substring(1,results[18].indexOf('.')+3));
                    labelMap.setText(results[39]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isInteger(String str,int index)
    {
        if(str==null||str.length()==0)
        {
            return false;
        }
        if((int)textFieldLat.getText().charAt(0)>=48&&(int)textFieldLat.getText().charAt(0)<=57)
        {
            if(index<str.length())
            {
                isInteger(str,index+1);
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {launch();}
}