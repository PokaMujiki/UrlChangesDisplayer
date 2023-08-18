package ru.softaria;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> yesterdayState = new HashMap<>();
        HashMap<String, String> todayState = new HashMap<>();

        yesterdayState.put("url 1", "html 1");
        yesterdayState.put("url 2", "html 2");
        yesterdayState.put("url 3", "html 3");
        yesterdayState.put("url 4", "html 4");

        todayState.put("url 3", "html 3");
        todayState.put("url 4", "html 4 changed");
        todayState.put("url 5", "html 5");
        todayState.put("url 6", "html 6");

        UrlChangeDetector detector = new UrlChangeDetector(yesterdayState, todayState);
        UrlChangesManager manager = new UrlChangesManager(detector);

        System.out.println(manager.generateReport());
    }
}