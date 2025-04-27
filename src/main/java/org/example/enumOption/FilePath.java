package org.example.enumOption;

public enum FilePath {
    WEATHERTXTPATH("src/main/java/org/example/FileScheduler/weatherScheduler.txt"),
    NEWSTXTPATH("src/main/java/org/example/FileScheduler/newsScheduler.txt"),
    CURRENCYTXTPATH("src/main/java/org/example/FileScheduler/currencyScheduler.txt");


    private final String name;

    FilePath(String name) {
        this.name = name;
    }
    public String getPath() {
        return name;
    }
}
