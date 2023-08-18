package ru.softaria;

import java.util.Set;

public class ReportTextCreator {
    private ReportTextCreator() {}

    public static String create(Set<String> deletedUrls, Set<String> createdUrls, Set<String> changedHtmlUrls) {
        StringBuilder mailText = new StringBuilder("""
                Здравствуйте, дорогая и.о. секретаря
                                
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                
                Исчезли следующие страницы:
                """);

        deletedUrls.forEach(url -> mailText.append(url).append("\n"));

        mailText.append("Появились следующие новые страницы:\n");
        createdUrls.forEach(url -> mailText.append(url).append("\n"));

        mailText.append("Изменились следующие страницы:\n");
        changedHtmlUrls.forEach(url -> mailText.append(url).append("\n"));

        mailText.append("""
                
                С уважением,
                автоматизированная система
                мониторинга.""");

        return mailText.toString();
    }
}
