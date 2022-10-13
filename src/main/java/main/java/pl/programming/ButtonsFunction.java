package main.java.pl.programming;

import javafx.stage.Stage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ButtonsFunction {

    public String randomLat() {
        return String.format(Locale.US,"%.2f",55-new Random().nextInt(5)+new Random().nextDouble(0.9));
    }
    public String randomLong() {
        return String.format(Locale.US,"%.2f",23-new Random().nextInt(11)+new Random().nextDouble(0.9));
    }
    public String getWords(String longitude, String latitude, Language language) {
        String link=null;
        if(isInteger(latitude)&&isInteger(longitude)) {
            link="https://api.what3words.com/v3/convert-to-3wa?coordinates="+latitude+"%2C"+longitude+"&clip-to-country=PL&language="+language.getCode()+"&key=I1G2OFF8";
        }
        else{
            ErrorWindow errorWindow=new ErrorWindow("You must enter the coordinates in the label");
                errorWindow.start(new Stage());
        }
        if(link!=null) {
            try {
                String returned;
                Connection connection=new Connection(link);
                returned=connection.returnConnection();
                if(!returned.isEmpty()) {
                    String[] results=returned.split("\"");
                    return results[31];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Error";
    }
    public String[] getCoordinates(String threeWords) {
        String regex = "[n+\\p{IsAlphabetic}\\p{S}.\\p{IsAlphabetic}\\p{S}\\p{IsAlphabetic}\\p{S}]";
        Pattern patter=Pattern.compile(regex);
        Matcher matcher=patter.matcher(threeWords);
        threeWords=URLEncoder.encode(threeWords, StandardCharsets.UTF_8);
        if(matcher.find()) {
            String link="https://api.what3words.com/v3/convert-to-coordinates?words="+threeWords+"&key=I1G2OFF8";
            try {
                Connection connection= new Connection(link);
                String returned = connection.returnConnection();
                if (!returned.isEmpty()) {
                    String[] results = returned.split("\"");
                    return new String[]{results[16].substring(1,results[16].indexOf('.')+3),results[18].substring(1,results[18].indexOf('.')+3),"https://what3words.com//"+results[31]};
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[]{"Error"};
    }
    public boolean isInteger(String str) {
        String regEx ="^\\d+.\\d+$";
        Pattern pattern=Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        return matcher.find();
    }
}