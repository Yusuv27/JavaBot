package org.example.Functional.News.FilterText;

public enum ContentSmile {
    RUSSIASMILE("\uD83C\uDDF7\uD83C\uDDFA"),
    IRANSMILE("\uD83C\uDDF9\uD83C\uDDEF"),
    USASMILE("\uD83C\uDDFA\uD83C\uDDF8"),
    BRITANIASMILE("\uD83C\uDDEC\uD83C\uDDE7"),
    UKRAINSMILE("\uD83C\uDDFA\uD83C\uDDE6");

    private final String name;

    ContentSmile(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
