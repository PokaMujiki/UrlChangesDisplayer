package ru.softaria;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReportTextCreatorTest {

    private static final Set<String> deletedUrls= new HashSet<>();
    private static final Set<String> createdUrls = new HashSet<>();
    private static final Set<String> changedHtmlUrls= new HashSet<>();

    @BeforeAll
    static void setupStates() {
        createdUrls.addAll(List.of("url 5", "url 6"));
        deletedUrls.addAll(List.of("url 1", "url 2"));
        changedHtmlUrls.addAll(List.of("url 3", "url 4"));
    }

    @Test
    void testCreate() {
        String report = ReportTextCreator.create(deletedUrls, createdUrls, changedHtmlUrls);

        String expected = """
                Здравствуйте, дорогая и.о. секретаря
                                
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                
                Исчезли следующие страницы:
                url 1
                url 2
                Появились следующие новые страницы:
                url 5
                url 6
                Изменились следующие страницы:
                url 3
                url 4
                                
                С уважением,
                автоматизированная система
                мониторинга.""";

        assertEquals(expected, report);
    }
}