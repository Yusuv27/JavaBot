package org.example.enumOption;

public enum WeatherSmile {
    CLEARWEATHER("☀\uFE0F"),
    LITTLECLOUDY("\uD83C\uDF25"),
    CLOUDY("☁\uFE0F"),
    SLEET("\uD83C\uDF28"),
    SNOW("\uD83C\uDF28"),
    SNOWANDRAIN("\uD83C\uDF27");

    private final String name;

    WeatherSmile(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
