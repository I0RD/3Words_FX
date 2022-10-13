package pl.programming;

import main.java.pl.programming.ButtonsFunction;
import main.java.pl.programming.Language;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ButtonsFunctionTest {
    private final ButtonsFunction buttonsFunction=new ButtonsFunction();
    @Test
    void should_ReturnTrue_When_StringIsNumber(){
        //given
        boolean expected =true;
        String example="123.12";
        //when
        boolean actual=buttonsFunction.isInteger(example);
        //then
        assertEquals(expected,actual);
    }
    @Test
    void should_ReturnFalse_When_StringIsNotNumber(){
        //given
        boolean expected =false;
        String example="12a.12";
        //when
        boolean actual=buttonsFunction.isInteger(example);
        //then
        assertEquals(expected,actual);
    }
    @RepeatedTest(10)
    void should_ReturnStringOfLat(){
        //given
        String maxLat="56";
        String minLat="49";
        int expectedMin=0;
        int expectedMax=0;
        //when
         String value=buttonsFunction.randomLat();
         int actualMin=value.compareTo(minLat);
         int actualMax=value.compareTo(maxLat);
        //then
        assertAll(
                () ->assertTrue(expectedMin<=actualMin),
                () ->assertTrue(expectedMax>=actualMax)
        );
    }
    @RepeatedTest(10)
    void should_ReturnStringOfLong(){
        //given
        String maxLong="24";
        String minLong="13";
        int expectedMin=0;
        int expectedMax=0;
        //when
        String value=buttonsFunction.randomLong();
        int actualMin=value.compareTo(minLong);
        int actualMax=value.compareTo(maxLong);
        //then
        assertAll(
                () ->assertTrue(expectedMin<=actualMin),
                () ->assertTrue(expectedMax>=actualMax)
        );
    }
    @Test
    void should_ReturnStringWords_WhenCoordinateIsCorrect() {
        //given
        String latitude="51.59";
        String longitude="23.29";
        String expected="helpless.snuggles.jots";
        //when
        String actual=buttonsFunction.getWords(longitude,latitude,new Language("English","en"));
        //then
        assertEquals(expected, actual);
    }
    @Test
    void should_ReturnStringArrayOfCoordinatesEndLink_WhenWordsIsCorrect() {
        //given
        String expectedLatitude="51.59";
        String expectedLongitude="23.29";
        String expectedLink="https://what3words.com//helpless.snuggles.jots";
        String words="helpless.snuggles.jots";
        //when
        String[] actual=buttonsFunction.getCoordinates(words);
        //then
        assertAll(
                () -> assertEquals(expectedLongitude, actual[0]),
                () -> assertEquals(expectedLatitude,actual[1]),
                () -> assertEquals(expectedLink,actual[2])
        );

    }
}