package pl.programming;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class MainWindow extends Application {
    private final Text labelWords=new Text("3 WORDS");
    private final Text labelLat=new Text("Latitude");
    private final Text labelLong=new Text("longitude");
    private TextField textFieldWords=new TextField(null);
    private TextField textFieldLat=new TextField(null);
    private TextField textFieldLong=new TextField(null);
    private final CheckBox checkBox=new CheckBox("pl");
    private final Button btnLonLat=new Button("Generate Lat/Lon");
    private final Button btnWords=new Button("Generate Words");
    private final Button btnRandom=new Button("Random");
    private final GridPane root =new GridPane();
    private boolean isPl=false;

    @Override
    public void start(Stage stage)  {
        stage.setTitle("3 Words coordinate FX");
        stage.setWidth(700);
        stage.setHeight(500);
        stage.setMinHeight(300);
        stage.setMinWidth(350);
        GridPane.setMargin(labelWords,new Insets(30, 5, 5, 50));
        GridPane.setMargin(textFieldWords,new Insets(5, 5, 5, 50));
        GridPane.setMargin(labelLat,new Insets(5, 5, 5, 50));
        GridPane.setMargin(textFieldLat,new Insets(5, 5, 5, 50));
        GridPane.setMargin(labelLong,new Insets(5, 5, 5, 50));
        GridPane.setMargin(textFieldLong,new Insets(5, 5, 5, 50));
        GridPane.setMargin(checkBox,new Insets(5, 5, 5, 100));
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
        root.add(checkBox,0,7);
        root.add(btnWords,1,3);
        btnWords.setOnAction(actionEvent ->  {
            randomWords();
        });
        root.add(btnLonLat,1,4);
        root.add(btnRandom,1,5);
        btnRandom.setOnAction(actionEvent ->  {
            randomLatLong();
        });
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.show();
    }
    private void randomLatLong()
    {

        textFieldLat.setText(String.valueOf(23-new Random().nextInt(11)+new Random().nextDouble(0.9)));
        textFieldLong.setText(String.valueOf(55-new Random().nextInt(5)+new Random().nextDouble(0.9)));
    }
    private void randomWords()
    {
        String link=null;
        if(isInteger(textFieldLat.getText(),0)&&isInteger(textFieldLong.getText(),0))
        {
            if(checkBox.isSelected())
            {
                link="https://api.what3words.com/v3/convert-to-3wa?"+ "coordinates="+textFieldLat.getText()+"%2C"+textFieldLong.getText()+"&language=pl&key=F8QT0PCM";
            }
            else{
                link="https://api.what3words.com/v3/convert-to-3wa?"+ "coordinates="+textFieldLat.getText()+"%2C"+textFieldLong.getText()+"&key=F8QT0PCM";
            }
        }
        else{
            Scene alert=new Scene(new Label("You need to give longitude and latitude!"));
            Stage alertWindow=new Stage();
            alertWindow.setScene(alert);
            alertWindow.setResizable(false);
            alertWindow.initStyle(StageStyle.UTILITY);
            alertWindow.setTitle("Alert!");
            alertWindow.initModality(Modality.APPLICATION_MODAL);
            alertWindow.setHeight(150);
            alertWindow.setAlwaysOnTop(true);
            alertWindow.show();
        }
        System.out.println(link);
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
                isInteger(str,index+=1);
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {launch();}
}