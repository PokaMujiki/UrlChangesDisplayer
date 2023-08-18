package ru.softaria;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UrlChangeDetectorTest {

    private static final HashMap<String, String> yesterdayState = new HashMap<>();
    private static final HashMap<String, String> todayState = new HashMap<>();

    @BeforeAll
    static void setupStates() {
        yesterdayState.put("url 1", "html 1");
        yesterdayState.put("url 2", "html 2");
        yesterdayState.put("url 3", "html 3");
        yesterdayState.put("url 4", "html 4");
        yesterdayState.put("unchanged url", "unchanged html");

        todayState.put("url 3", "html 3 changed");
        todayState.put("url 4", "html 4 changed");
        todayState.put("unchanged url", "unchanged html");
        todayState.put("url 5", "html 5");
        todayState.put("url 6", "html 6");
    }

    @Test
    void testFindChangedHtmlUrls() {
        var expected = new HashSet<>(List.of("url 3", "url 4"));
        var detector = new UrlChangeDetector(yesterdayState, todayState);

        var found = detector.findChangedHtmlUrls();

        assertEquals(expected, found);
    }

    @Test
    void testFindDeletedUrls() {
        var expected = new HashSet<>(List.of("url 1", "url 2"));
        var detector = new UrlChangeDetector(yesterdayState, todayState);

        var found = detector.findDeletedUrls();

        assertEquals(expected, found);
    }

    @Test
    void testFindCreatedUrls() {
        var expected = new HashSet<>(List.of("url 5", "url 6"));
        var detector = new UrlChangeDetector(yesterdayState, todayState);

        var found = detector.findCreatedUrls();

        assertEquals(expected, found);
    }
}