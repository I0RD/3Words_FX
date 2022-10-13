package pl.programming;

import main.java.pl.programming.Connection;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    private String exampleLink;
    @Test
    public void should_ReturnURLException_WhenBadStringURL() {
        //given
        exampleLink ="AAAA";
        //when
        Executable executable=() ->new Connection(exampleLink);
        //then
        assertThrows(
                MalformedURLException.class,executable
        );
    }
    @Test
    public void should_ReturnString_WhenStringURLisGood() {
        //given
        exampleLink ="https://api.what3words.com/v3/convert-to-coordinates?words=disconcertingly.electrodes.pouches&key=I1G2OFF8";
        String expected ="{\"country\":\"BY\",\"square\":{\"southwest\":{\"lng\":23.859969,\"lat\":53.809994},\"northeast\":{\"lng\":23.860015,\"lat\":53.81002}},\"nearestPlace\":\"Sapotskin, Grodno Region\",\"coordinates\":{\"lng\":23.859992,\"lat\":53.810007},\"words\":\"disconcertingly.electrodes.pouches\",\"language\":\"en\",\"map\":\"https:\\/\\/w3w.co\\/disconcertingly.electrodes.pouches\"}";
        //when
        String actual=null;
        try {
             actual=new Connection(exampleLink).returnConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //then
        assertEquals(expected, actual);
    }
    @Test
    public void should_ReturnRuntimeException_WhenStringURLisWrong() {
        //given
        exampleLink ="https://api.what3words.com/v3/convert-to-coordinates?words=disconcertingly.el$ctrodes.pouches&key=I1G2OFF8";
        //when
        Executable executable=() ->new Connection(exampleLink).returnConnection();
        //then
        assertThrows(
                RuntimeException.class,executable
        );
    }
}