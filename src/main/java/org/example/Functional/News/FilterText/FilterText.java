package org.example.Functional.News.FilterText;

public class FilterText {
    public static String filterMediaMetricsParser(String text){

        text = text.replace(" - \n","\n\n");


        return text;
    }

}
