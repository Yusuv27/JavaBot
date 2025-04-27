package org.example.Functional.News.FilterText;

import java.util.HashMap;
import java.util.Map;

import static org.example.enumOption.ContentSmile.*;

public class FilterText {

    private static final Map<String, String> replacements = new HashMap<>();

    static {
        replacements.put("Россия", RUSSIASMILE.getName() + "Россия");
        replacements.put("Россию", RUSSIASMILE.getName() + "Россию");
        replacements.put("России", RUSSIASMILE.getName() + "России");
        replacements.put("россиян", RUSSIASMILE.getName() + "россиян");
        replacements.put("Россияне", RUSSIASMILE.getName() + "Россияне");
        replacements.put("россияне", RUSSIASMILE.getName() + "россияне");
        replacements.put("ВСРФ", RUSSIASMILE.getName() + "ВСРФ");
        replacements.put("ВС РФ", RUSSIASMILE.getName() + "ВС РФ");
        replacements.put("РФ", RUSSIASMILE.getName() + "РФ");
        replacements.put("Российская федерация", RUSSIASMILE.getName() + "Российская федерация");
        replacements.put("Российскую федерацию", RUSSIASMILE.getName() + "Российскую федерацию");
        replacements.put("российская армия", RUSSIASMILE.getName() + "российская армия");
        replacements.put("Российская армия", RUSSIASMILE.getName() + "Российская армия");
        replacements.put("российскую армию", RUSSIASMILE.getName() + "российскую армию");
        replacements.put("Российскую армию", RUSSIASMILE.getName() + "Российскую армию");
        replacements.put("Путин", RUSSIASMILE.getName() + "Путин");
        replacements.put("Песков", RUSSIASMILE.getName() + "Песков");
        replacements.put("Кремль", RUSSIASMILE.getName() + "Кремль");
        replacements.put("Шойгу", RUSSIASMILE.getName() + "Шойгу");
        replacements.put("Герасим", RUSSIASMILE.getName() + "Герасим");
        replacements.put("ФСБ", RUSSIASMILE.getName() + "ФСБ");
        replacements.put("Нарышкин", RUSSIASMILE.getName() + "Нарышкин");

        replacements.put("Иран", IRANSMILE.getName() + "Иран");


        replacements.put("Трамп", USASMILE.getName() + "Трамп");
        replacements.put("США", USASMILE.getName() + "США");
        replacements.put("Уиткофф", USASMILE.getName() + "Уиткофф");
        replacements.put("Белый дом", USASMILE.getName() + "Белый дом");
        replacements.put("ЦРУ", USASMILE.getName() + "ЦРУ");
        replacements.put("ФБР", USASMILE.getName() + "ФБР");

        replacements.put("Великобритани", BRITANIASMILE.getName() + "Великобритани");
        replacements.put("Британи", BRITANIASMILE.getName() + "Британи");

        replacements.put("Украин", UKRAINSMILE.getName() + "Украин");
        replacements.put("ВСУ", UKRAINSMILE.getName() + "ВСУ");
        replacements.put("Зеленск", UKRAINSMILE.getName() + "Зеленск");
    }

    public static String filterMediaMetricsParser(String text) {

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }


        return text;
    }
}
